package org.andrius.camel.routes.sentence;

import org.andrius.camel.util.Constants;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static org.andrius.camel.util.Constants.NOUN;
import static org.andrius.camel.util.Constants.VERB;

@Component
public class SentenceAggregationStrategy implements AggregationStrategy, Predicate {

    private static final Logger logger = LoggerFactory.getLogger(SentenceAggregationStrategy.class);
    private final String sentencePattern = String.format("The %s %s %s", Constants.ADJECTIVE, NOUN, Constants.VERB);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String typeOfWord = newExchange.getMessage().getHeader(Constants.WORD_TYPE_HEADER, String.class);
        String word = newExchange.getMessage().getBody(String.class);
        if (oldExchange == null) {
            String newSentence = sentencePattern.replace(typeOfWord, word);
            newExchange.getMessage().setBody(newSentence, String.class);
            logger.info("Starting new sentence: {}", newSentence);
            return newExchange;
        }

        String oldSentence = oldExchange.getMessage().getBody(String.class);
        if (oldSentence.contains(typeOfWord)) {
            String updatedSentence = oldSentence.replace(typeOfWord, word);
            oldExchange.getMessage().setBody(updatedSentence);
            logger.info("Sentence updated: {}", updatedSentence);
        } else {
            logger.info("Skipping the word \"{}\" - sentence already contain {}", word, getWordTypeLogMessage(typeOfWord));
        }
        return oldExchange;
    }

    @Override
    public boolean matches(Exchange exchange) {
        String body = exchange.getMessage().getBody(String.class);
        return !body.contains(NOUN) && !body.contains(Constants.VERB) && !body.contains(Constants.ADJECTIVE);
    }

    private String getWordTypeLogMessage(String typeOfWord) {
        if (typeOfWord.equals(NOUN) || typeOfWord.equals(VERB)) {
            return "a " + typeOfWord.toLowerCase();
        } else {
            return "an " + typeOfWord.toLowerCase();
        }
    }
}
