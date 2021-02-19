package com.example.pets.controller;

import com.example.pets.dto.PetDTO;
import com.example.pets.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pets")
@AllArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping(value = "/{id}")
    public List<PetDTO> getAllPetsByOwnerId(@PathVariable long id) {
        return petService.getPetsByOwnerId(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePet(@PathVariable long id) {
        try {
            petService.deletePet(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

}
