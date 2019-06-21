package com.zhaohuabing.demo;

import com.zhaohuabing.demo.service.BankTransaction;
import com.zhaohuabing.demo.service.DBAccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
	public BankTransaction bankTransaction() {
		return new BankTransaction();
	}

	@Bean
	public DBAccess dbAccess() {
		return new DBAccess();
	}

	public static void main(String[] args) {
		SpringApplication.run(IstioOpentracingDemoApplication.class, args);
	}

}
