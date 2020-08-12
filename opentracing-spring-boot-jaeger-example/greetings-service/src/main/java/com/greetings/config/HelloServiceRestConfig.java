package com.greetings.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.propagation.B3TextMapCodec;
import io.jaegertracing.zipkin.ZipkinSender;
import io.opentracing.propagation.Format;
import io.jaegertracing.internal.reporters.RemoteReporter;

import io.opentracing.Tracer;

@Configuration
@EnableAutoConfiguration
public class HelloServiceRestConfig {

  @Bean("helloRestTemplate")
  public RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate;
  }

  @Bean
    public Tracer jaegerTracer() throws Exception {
        return new JaegerTracer.Builder("jaeger-demo")
			        .registerInjector(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec())
			        .registerExtractor(Format.Builtin.HTTP_HEADERS, new B3TextMapCodec())
			        .build();
    }
}