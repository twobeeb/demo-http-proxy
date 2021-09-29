package com.example;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
@Property(name = "test.exception.enabled", value = "false")
class DemoHttpProxyTestSuccess {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void test_micronaut_core_6178(){
        String s = client.toBlocking().retrieve("/backend/call");
        Assertions.assertEquals("Yes !", s);
    }

}
