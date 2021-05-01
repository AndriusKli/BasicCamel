package org.andrius.camel.predicate;

import org.andrius.camel.util.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class SentenceIsComplete implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        String body = exchange.getMessage().getBody(String.class);
        if (body.contains(Constants.NOUN) || body.contains(Constants.VERB) || body.contains(Constants.ADJECTIVE)) {
            return false;
        }
        return true;
    }
}
