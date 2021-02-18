package com.example.pets.exception;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(long id) {
        super("The pet not found with id: " + id);
    }
}
