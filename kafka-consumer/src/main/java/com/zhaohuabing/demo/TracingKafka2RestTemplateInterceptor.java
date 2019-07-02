package com.zhaohuabing.demo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.common.header.Headers;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.contrib.kafka.TracingKafkaUtils;
import io.opentracing.contrib.spring.web.client.HttpHeadersCarrier;
import io.opentracing.contrib.spring.web.client.RestTemplateSpanDecorator;
import io.opentracing.propagation.Format;
import io.opentracing.tag.Tags;
import io.opentracing.util.GlobalTracer;

/**
 * OpenTracing Spring RestTemplate integration. This interceptor creates tracing
 * data for all outgoing requests as child span of Kafka span.
 * 
 * Modified based on java-spring-web TracingRestTemplateInterceptor.java
 *
 */
public class TracingKafka2RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    private static final Log log = LogFactory.getLog(TracingKafka2RestTemplateInterceptor.class);

    private List<RestTemplateSpanDecorator> spanDecorators;
    private Tracer tracer;
    private Headers headers;

    public TracingKafka2RestTemplateInterceptor(Headers headers) {
        this.headers = headers;
        this.tracer = GlobalTracer.get();
        this.spanDecorators = Collections
                .<RestTemplateSpanDecorator>singletonList(new RestTemplateSpanDecorator.StandardTags());
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        ClientHttpResponse httpResponse;

        SpanContext parentSpanContext = TracingKafkaUtils.extractSpanContext(headers, tracer);

        Span span = tracer.buildSpan(httpRequest.getMethod().toString()).asChildOf(parentSpanContext)
                .withTag(Tags.SPAN_KIND.getKey(), Tags.SPAN_KIND_CLIENT).start();
        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new HttpHeadersCarrier(httpRequest.getHeaders()));

        for (RestTemplateSpanDecorator spanDecorator : spanDecorators) {
            try {
                spanDecorator.onRequest(httpRequest, span);
            } catch (RuntimeException exDecorator) {
                log.error("Exception during decorating span", exDecorator);
            }
        }

        try (Scope scope = tracer.activateSpan(span)) {
            httpResponse = execution.execute(httpRequest, body);
            for (RestTemplateSpanDecorator spanDecorator : spanDecorators) {
                try {
                    spanDecorator.onResponse(httpRequest, httpResponse, span);
                } catch (RuntimeException exDecorator) {
                    log.error("Exception during decorating span", exDecorator);
                }
            }
        } catch (Exception ex) {
            for (RestTemplateSpanDecorator spanDecorator : spanDecorators) {
                try {
                    spanDecorator.onError(httpRequest, ex, span);
                } catch (RuntimeException exDecorator) {
                    log.error("Exception during decorating span", exDecorator);
                }
            }
            throw ex;
        } finally {
            span.finish();
        }

        return httpResponse;
    }
}
