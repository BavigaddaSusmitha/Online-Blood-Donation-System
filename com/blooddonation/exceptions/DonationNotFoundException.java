package com.blooddonation.exceptions;

public class DonationNotFoundException extends Exception {

    public DonationNotFoundException(int id) {
        super("Donation not found with ID: " + id);
    }
}