package com.psipher.application.exceptions;

public class DDBException extends Exception {
    public DDBException(String dbException) {
        super(dbException);
    }

}
