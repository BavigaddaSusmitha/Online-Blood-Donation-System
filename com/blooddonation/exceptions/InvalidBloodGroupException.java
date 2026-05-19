package com.blooddonation.exceptions;

public class InvalidBloodGroupException extends Exception {

    public InvalidBloodGroupException(String bloodGroup) {
        super("Invalid blood group entered: " + bloodGroup
                + ". Valid values are: A+, A-, B+, B-, O+, O-, AB+, AB-");
    }
}