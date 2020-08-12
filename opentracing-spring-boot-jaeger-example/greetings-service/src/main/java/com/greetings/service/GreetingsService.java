package com.greetings.service;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.TextMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GreetingsService {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingsService.class);

    @Autowired
    private Tracer tracer;

    private RestTemplate restTemplate;
    private final String helloServiceHost;

    public GreetingsService(@Value("localhost") String helloServiceHost,
                            @Qualifier("helloRestTemplate") RestTemplate restTemplate) {
        this.helloServiceHost = helloServiceHost;
        this.restTemplate = restTemplate;
    }

    public String printHello() {
        LOG.info("printHello");
        Span span = tracer.buildSpan("GreetingsService printHello").start();
        LOG.info("span: " + span);
        String result;
        String url = "http://localhost:5000";

        //tracer.inject(span.context(), Format.Builtin.TEXT_MAP, carrier);
        result = restTemplate.getForObject(url, String.class);

        span.finish();
        return result;
    }
}
