package com.zhaohuabing.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.opentracing.Tracer;

/**
 * Huabing Zhao
 */
@SpringBootApplication
public class IstioOpentracingDemoApplication {
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Bean
	public Tracer jaegerTracer() {
		// The following environment variables need to set
		// JAEGER_ENDPOINT="http://10.42.126.171:28019/api/traces"
		// JAEGER_PROPAGATION="b3"
		// JAEGER_TRACEID_128BIT="true" Use 128bit tracer id to be compatible with the
		// trace id generated by istio/envoy
		return Configuration.fromEnv("eshop-opentracing").getTracer();
	}

	public static void main(String[] args) {
		SpringApplication.run(IstioOpentracingDemoApplication.class, args);
	}

}
