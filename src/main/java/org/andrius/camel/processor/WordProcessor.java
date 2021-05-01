package org.andrius.camel.processor;

import org.andrius.camel.util.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class WordProcessor implements Processor {

    private static final String DELIMITER = "\\|";

    @Override
    public void process(Exchange exchange) {
        String body = exchange.getIn().getBody(String.class);
        String[] messageChunks = body.split(DELIMITER);
        String typeOfWord = messageChunks[0].strip();
        String word = messageChunks[1].strip();

        exchange.getMessage().setHeader(Constants.WORD_TYPE_HEADER, typeOfWord);
        exchange.getMessage().setBody(word);
    }
}
