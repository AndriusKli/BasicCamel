package com.example.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PriceProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(PriceProcessor.class);

    public PriceProcessor() {
        logger.warn("INITIALIZING PRICE PROCESSOR!");
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String content = exchange.getIn().getBody(String.class);
        logger.warn("PriceProcessor processing!");
        logger.warn("Content: {}", content);
        exchange.getIn().setBody(content + "\nAAAAAAA!");
    }
}
