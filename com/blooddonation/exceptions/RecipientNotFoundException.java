package com.blooddonation.exceptions;

public class RecipientNotFoundException extends Exception {

    public RecipientNotFoundException(int id) {
        super("Recipient not found with ID: " + id);
    }
}