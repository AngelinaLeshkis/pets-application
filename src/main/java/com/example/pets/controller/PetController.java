package com.example.pets.controller;

import com.example.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable long id) {
        try {
            petService.deletePet(id);
            return new ResponseEntity<>(OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

}
