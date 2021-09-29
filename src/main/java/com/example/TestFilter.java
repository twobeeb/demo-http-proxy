package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.client.ProxyHttpClient;
import io.micronaut.http.filter.HttpServerFilter;
import io.micronaut.http.filter.ServerFilterChain;
import io.micronaut.runtime.server.EmbeddedServer;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

import java.net.URI;
import java.net.URISyntaxException;

@Filter("/connect-proxy/**")
public class TestFilter implements HttpServerFilter {
    @Inject
    ProxyHttpClient client;

    @Inject
    EmbeddedServer server;


    @Override
    public Publisher<MutableHttpResponse<?>> doFilter(HttpRequest<?> request, ServerFilterChain chain) {
        MutableHttpRequest<?> mutableHttpRequest = request.mutate()
                .uri(uri -> uri.scheme(server.getScheme())
                        .host(server.getHost())
                        .port(server.getPort())
                        .replacePath("/backend/value")
                );
        return client.proxy(mutableHttpRequest);
    }
}
