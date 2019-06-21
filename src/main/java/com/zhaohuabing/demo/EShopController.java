package com.zhaohuabing.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Huabing Zhao
 */
@RestController
public class EShopController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        String result = "";
        // Use HTTP GET in this demo. In a real world use case,We should use HTTP POST
        // instead.
        // The three services are bundled in one jar for simplicity. To make it work,
        // define three services in Kubernets.
        result += restTemplate.exchange("http://inventory:8080/createOrder", HttpMethod.GET,
                new HttpEntity<>(passTracingHeader(headers)), String.class).getBody();
        result += "<BR>";
        result += restTemplate.exchange("http://billing:8080/payment", HttpMethod.GET,
                new HttpEntity<>(passTracingHeader(headers)), String.class).getBody();
        result += "<BR>";
        result += restTemplate.exchange("http://delivery:8080/arrangeDelivery", HttpMethod.GET,
                new HttpEntity<>(passTracingHeader(headers)), String.class).getBody();
        return result;
    }

    private HttpHeaders passTracingHeader(HttpHeaders headers) {
        HttpHeaders tracingHeaders = new HttpHeaders();
        extractHeader(headers, tracingHeaders, "x-request-id");
        extractHeader(headers, tracingHeaders, "x-b3-traceid");
        extractHeader(headers, tracingHeaders, "x-b3-spanid");
        extractHeader(headers, tracingHeaders, "x-b3-parentspanid");
        extractHeader(headers, tracingHeaders, "x-b3-sampled");
        extractHeader(headers, tracingHeaders, "x-b3-flags");
        extractHeader(headers, tracingHeaders, "x-ot-span-context");
        return tracingHeaders;
    }

    private void extractHeader(HttpHeaders headers, HttpHeaders extracted, String key) {
        List<String> vals = headers.get(key);
        if (vals != null && !vals.isEmpty()) {
            extracted.put(key, Arrays.asList(vals.get(0)));
        }
    }
}
