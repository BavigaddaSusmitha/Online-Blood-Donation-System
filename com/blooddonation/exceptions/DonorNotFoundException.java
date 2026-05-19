package com.blooddonation.exceptions;

public class DonorNotFoundException extends Exception {

    public DonorNotFoundException(int id) {
        super("Donor not found with ID: " + id);
    }
}