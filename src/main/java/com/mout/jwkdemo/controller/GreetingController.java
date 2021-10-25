package com.mout.jwkdemo.controller;

import com.mout.jwkdemo.api.APIGreeting;
import com.mout.jwkdemo.security.SignedResponseEntityFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final SignedResponseEntityFactory signedResponseEntityFactory;

    public GreetingController(SignedResponseEntityFactory signedResponseEntityFactory) {
        this.signedResponseEntityFactory = signedResponseEntityFactory;
    }

    @GetMapping("/greeting")
    ResponseEntity<byte[]> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        APIGreeting greeting = doGreeting(name);
        return signedResponseEntityFactory.create(greeting);
    }

    APIGreeting doGreeting(String name) {
        return new APIGreeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
