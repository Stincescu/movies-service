package com.pentalog.pentastagiu.web.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends RuntimeException {
    private String name;
    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String id;

    public EntityNotFoundException(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
