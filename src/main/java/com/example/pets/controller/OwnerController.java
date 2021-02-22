package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Owner;
import com.example.pets.service.CatService;
import com.example.pets.service.DogService;
import com.example.pets.service.OwnerService;
import com.example.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final DogService dogService;
    private final CatService catService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.saveOwner(owner), CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner,
                                             @PathVariable long id) {
        try {
            ownerService.updateOwner(owner, id);
            return new ResponseEntity<>(owner, OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOwner(@PathVariable long id) {
        try {
            ownerService.deleteOwner(id);
            return new ResponseEntity<>(OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        try {
            List<Owner> owners = ownerService.getAllOwners();
            return new ResponseEntity<>(owners, OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerById(id), OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<Owner> getOwnerByPetId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerByPetId(id), OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}/dogs")
    public ResponseEntity<List<DogDTO>> getAllDogsByOwnerId(@PathVariable long id) {
        try {
            List<DogDTO> dogs = dogService.getAllByOwnerId(id);
            return new ResponseEntity<>(dogs, OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}/cats")
    public ResponseEntity<List<CatDTO>> getAllCatsByOwnerId(@PathVariable long id) {
        try {
            List<CatDTO> cats = catService.getAllCatsByOwnerId(id);
            return new ResponseEntity<>(cats, OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}/pets")
    public ResponseEntity<List<PetDTO>> getAllPetsByOwnerId(@PathVariable long id) {
        try {
            List<PetDTO> pets = petService.getPetsByOwnerId(id);
            return new ResponseEntity<>(pets, OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }
}
