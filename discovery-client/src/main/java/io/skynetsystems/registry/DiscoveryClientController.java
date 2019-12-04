package io.skynetsystems.registry;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@RestController
public class DiscoveryClientController {

    @Autowired
    private RestTemplate rest;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value="salutation", defaultValue="Hello") String salutation, @RequestParam(value="name", defaultValue="") String name) {
        return getGreeting(salutation, name);
    }

    @HystrixCommand(fallbackMethod = "getFallbackGreeting")
    private String getGreeting(String salutation, String name) {
        URI uri = UriComponentsBuilder.fromUriString("//discovery-service/health" )
                .build()
                .toUri();
        return rest.getForObject(uri, String.class);
    }

    @SuppressWarnings("unused")
	private String getFallbackGreeting() {
        return "Oops, the service is not currently available";
    }
}

