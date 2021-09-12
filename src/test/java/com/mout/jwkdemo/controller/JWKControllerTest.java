package com.mout.jwkdemo.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class JWKControllerTest {

    @Autowired
    private JWKController jwkController;

    @BeforeEach
    void initialize() {
        RestAssuredMockMvc.standaloneSetup(jwkController);
    }

    @Test
    void jwk() {
        RestAssuredMockMvc.given()
                .when()
                .get("/jwk")
                .then()
                .status(OK)
                .contentType(JSON)
                .body(
                        "keys.size()", is(1),
                        "keys[0].kty", is("EC"),
                        "keys[0].use", is("sig"),
                        "keys[0].crv", is("P-256"),
                        "keys[0].kid", is("wSCfJsVZ-mUKAJomseVFhgEtp5CufCzYG4BeWxDsWTs"),
                        "keys[0].x", is("DWqbyxG5nPkFd6SesIfN8Mwb_OAiqXOWRUNmRLLqgxI"),
                        "keys[0].y", is("MI9FBtu7jB-fzgJ3UFHq_NbbsiLBtuCmWeX1SKuxtRY"),
                        "keys[0].d", nullValue());
    }
}
