package com.example;



import java.util.List;


public class ResourceValidationException extends RuntimeException {
    private static final long serialVersionUID = 32400191899153204L;

    private final List<String> validationErrors;


    private final String kind;

    private final String name;

    public ResourceValidationException(List<String> validationErrors, String kind, String name) {
        this.validationErrors = validationErrors;
        this.kind = kind;
        this.name = name;
    }

}
