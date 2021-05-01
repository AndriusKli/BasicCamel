package org.andrius.camel.routes.sentence;

import org.andrius.camel.predicate.SentenceIsComplete;
import org.andrius.camel.processor.WordProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SentenceRouteConfiguration extends RouteBuilder {

    private final WordProcessor wordProcessor;
    private final SentenceAggregationStrategy sentenceAggregationStrategy;
    private final SentenceIsComplete sentenceIsComplete;

    @Autowired
    public SentenceRouteConfiguration(WordProcessor wordProcessor,
                                      SentenceAggregationStrategy sentenceAggregationStrategy,
                                      SentenceIsComplete sentenceIsComplete) {
        this.wordProcessor = wordProcessor;
        this.sentenceAggregationStrategy = sentenceAggregationStrategy;
        this.sentenceIsComplete = sentenceIsComplete;
    }

    @Override
    public void configure() {
        from("jms:queue:sampleQueue")
                .routeId("sentence_route")
                .autoStartup(true)
                .process(wordProcessor)
                .aggregate(constant(true), sentenceAggregationStrategy)
                .log("Sentence aggregation complete: ${body}");
    }
}
