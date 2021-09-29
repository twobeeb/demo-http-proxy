package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;


@Controller("/backend")
public class TestController {

    @Inject
    TestClient client;

    @Get("/call")
    public String call(){
        return client.list();
    }
    @Get("/value")
    public String list(){
        return "Yes !";
    }

}
