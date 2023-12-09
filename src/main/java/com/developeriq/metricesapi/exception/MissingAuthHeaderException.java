package com.developeriq.metricesapi.exception;

public class MissingAuthHeaderException extends Exception {
    public MissingAuthHeaderException(String errorMsg) {
        super(errorMsg);
    }

}
