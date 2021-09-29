package com.example;

import com.example.Status.StatusDetails;
import com.example.Status.StatusPhase;
import com.example.Status.StatusReason;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Status.StatusDetails.*;

@Controller("/errors")
@Requires(property = "test.exception.enabled", notEquals = StringUtils.FALSE)
public class ExceptionHandlerController {


    @Error(global = true, status = HttpStatus.NOT_FOUND)
    public HttpResponse<Status> error(HttpRequest<?> request) {
        var status = Status.builder()
                .status(StatusPhase.Failed)
                .message("Not Found")
                .reason(StatusReason.NotFound)
                .code(HttpStatus.NOT_FOUND.getCode())
                .build();

        return HttpResponse.status(HttpStatus.NOT_FOUND)
                .body(status);
    }

    // if we don't know the exception
    @Error(global = true)
    public HttpResponse<Status> error(HttpRequest<?> request, Exception exception) {
        var status = Status.builder()
                .status(StatusPhase.Failed)
                .message("Internal server error")
                .reason(StatusReason.InternalError)
                .details(StatusDetails.builder()
                        .causes(List.of(exception.toString()))
                        .build())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.getCode())
                .build();
        return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(status);
    }

}
