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
                        "keys.size()", is(2),
                        "keys[0].kty", is("EC"),
                        "keys[0].use", is("sig"),
                        "keys[0].crv", is("P-256"),
                        "keys[0].kid", is("wSCfJsVZ-mUKAJomseVFhgEtp5CufCzYG4BeWxDsWTs"),
                        "keys[0].x", is("DWqbyxG5nPkFd6SesIfN8Mwb_OAiqXOWRUNmRLLqgxI"),
                        "keys[0].y", is("MI9FBtu7jB-fzgJ3UFHq_NbbsiLBtuCmWeX1SKuxtRY"),
                        "keys[0].d", nullValue(),
                        "keys[1].kty", is("RSA"),

                        "keys[1].e", is("AQAB"),
                        "keys[1].use", is("sig"),
                        "keys[1].kid", is("evsHHx79Cc9t4tdX4fYcJskWNRZ57_5PjFC-VUXZNWg"),
                        "keys[1].n", is("n83dROgV5c5Ng1OcQKNOlsww-Nb42V1E3nQuYt9_r8lPueon6WFVsORhpQQ8uMMMDi7wGzt45jtIqcsROJHJ-WNzsvrk2LahhWiqTHaPvQUp1bQN_D1g_axeAgNaSZcVuWtKCCTM5-lzSeUVOkyymrG2vFQRcPYFV76fbYDyzAB-ULvMnX0JgfIe4ba2CkOfq35b50AsXiTZD2F-IwiL2wQ0nZrDn9vaoM8_miIWarObEBWTmbZi5mt0W1DR8MlxptVpiUihTv2w1MlLkfPZrStz3KB3YPOEfsjH-30dJOomsHoOzEypE-OwhgAfsKERy7wfU6OQ6Div4gkcxDJSMQ"),
                        "keys[1].p", nullValue(),
                        "keys[1].q", nullValue(),
                        "keys[1].d", nullValue(),
                        "keys[1].qi", nullValue(),
                        "keys[1].dp", nullValue(),
                        "keys[1].dq", nullValue());
    }
}
