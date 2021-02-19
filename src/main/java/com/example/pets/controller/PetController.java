package com.example.pets.controller;

import com.example.pets.dto.PetDTO;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<PetDTO>> getAllPetsByOwnerId(@PathVariable long id) {
        try {
            List<PetDTO> pets = petService.getPetsByOwnerId(id);
            return new ResponseEntity<>(pets, OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable long id) {
        try {
            petService.deletePet(id);
            return new ResponseEntity<>(OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

}
