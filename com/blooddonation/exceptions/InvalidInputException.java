package com.blooddonation.exceptions;

public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super("Invalid Input: " + message);
    }
}