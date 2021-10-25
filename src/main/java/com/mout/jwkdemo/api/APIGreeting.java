package com.mout.jwkdemo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class APIGreeting {

    @JsonProperty
    private final long id;

    @JsonProperty
    private final String content;

    public APIGreeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
