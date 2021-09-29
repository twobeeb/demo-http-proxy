package com.example;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;

@Client("/connect-proxy")
public interface TestClient {
    @Get("/list")
    String list();
}
