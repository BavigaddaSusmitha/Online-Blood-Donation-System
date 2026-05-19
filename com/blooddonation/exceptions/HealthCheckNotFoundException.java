package com.blooddonation.exceptions;

public class HealthCheckNotFoundException extends Exception {

    public HealthCheckNotFoundException(int id) {
        super("Health Check not found with ID: " + id);
    }
}