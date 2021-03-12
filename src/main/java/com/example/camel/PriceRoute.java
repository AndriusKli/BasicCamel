package com.example.camel;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PriceRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(PriceRoute.class);

    private final PriceProcessor priceProcessor;

    private static final String RESOURCE_DIRECTORY = "/Users/andrius.kliunka/Source/playground/camel/src/main/resources/";

    public PriceRoute() {
        this.priceProcessor = new PriceProcessor();
    }

    @Override
    public void configure() {
//        from("timer:myTimer?period=500&repeatCount=5").process(exchange -> System.out.println("HELLO!"));
        from("file:" + RESOURCE_DIRECTORY + "?fileName=prices.txt")
                .log("PROCESSING FILE!")
                .process(priceProcessor)
                .to("file:" + RESOURCE_DIRECTORY + "?fileName=processedFile.txt");
    }
}
