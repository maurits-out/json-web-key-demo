package com.mout.jwkdemo.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class GreetingControllerTest {

    @Autowired
    private GreetingController greetingController;

    @BeforeEach
    void initialize() {
        RestAssuredMockMvc.standaloneSetup(greetingController);
    }

    @Test
    void greetWithoutName() {
        RestAssuredMockMvc.given()
                .when()
                .get("/greeting")
                .then()
                .status(OK)
                .contentType(JSON)
                .body("id", greaterThan(0), "content", is("Hello, World!"));
    }

    @Test
    void greetWithName() {
        RestAssuredMockMvc.given()
                .queryParam("name", "Maurits")
                .when()
                .get("/greeting")
                .then()
                .status(OK)
                .contentType(JSON)
                .body("id", greaterThan(0), "content", is("Hello, Maurits!"));
    }
}
