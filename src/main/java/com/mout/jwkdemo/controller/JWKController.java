package com.mout.jwkdemo.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JWKController {

    private final JWKSet jwkSet;

    public JWKController(JWKSet jwkSet) {
        this.jwkSet = jwkSet;
    }

    @GetMapping("/jwk")
    Map<String, Object> jwk() {
        return jwkSet.toJSONObject(true);
    }
}
