package com.blooddonation.exceptions;

public class BloodBankNotFoundException extends Exception {

    public BloodBankNotFoundException(int id) {
        super("Recipient not found with ID: " + id);
    }
}