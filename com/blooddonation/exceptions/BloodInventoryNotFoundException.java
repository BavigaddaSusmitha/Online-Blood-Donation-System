package com.blooddonation.exceptions;

public class BloodInventoryNotFoundException extends Exception {

    public BloodInventoryNotFoundException(int id) {
        super("Blood Inventory not found with ID: " + id);
    }
}