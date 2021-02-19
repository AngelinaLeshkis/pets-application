package com.example.pets.exception;

public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(long id) {
        super("The owner not found with id: " + id);
    }
}
