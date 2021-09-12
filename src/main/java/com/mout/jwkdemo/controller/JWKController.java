package com.mout.jwkdemo.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JWKController {

    private final Map<String, Object> jwkJson;

    public JWKController(JWKSet jwkSet) {
        this.jwkJson = jwkSet.toJSONObject(true);
    }

    @GetMapping("/jwk")
    Map<String, Object> jwk() {
        return jwkJson;
    }
}
