package com.developeriq.metricesapi.exception;

public class UnAuthorisedException extends Exception{
    public UnAuthorisedException(String errorMsg) {
        super(errorMsg);
    }
}
