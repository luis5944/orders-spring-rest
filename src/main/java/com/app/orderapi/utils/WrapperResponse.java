package com.app.orderapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class WrapperResponse<T> {
    private boolean ok;
    private String message;
    private T body;


    public WrapperResponse(boolean ok, String message, T body) {
        this.ok = ok;
        this.message = message;
        this.body = body;
    }

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity(this, status);
    }
    public ResponseEntity<WrapperResponse<T>> createResponse() {
        return new ResponseEntity(this, HttpStatus.OK);
    }

    public WrapperResponse() {
    }


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
