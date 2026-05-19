package com.blooddonation.exceptions;

public class BloodRequestNotFoundException extends Exception {

    public BloodRequestNotFoundException(int id) {
        super("Blood Request not found with ID: " + id);
    }
}