package com.example.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamelTest {

    @Test
    void test() throws Exception {
//        CamelContext camelContext = new DefaultCamelContext();
//        camelContext.addRoutes(new RouteBuilder() {
//            @Override
//            public void configure() throws Exception {
//                from("direct:source").process(exchange -> System.out.println("HEELLOOO"));
//            }
//        });
//        ProducerTemplate template = camelContext.createProducerTemplate();
//        camelContext.start();
//
//        template.sendBody("direct:source", "test");
////        Thread.sleep(3000);
////        camelContext.stop();
    }
}
