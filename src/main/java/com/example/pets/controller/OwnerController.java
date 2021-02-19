package com.example.pets.controller;

import com.example.pets.entity.Owner;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.saveOwner(owner), CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner, @PathVariable long id) {
        try {
            ownerService.updateOwner(owner, id);
            return new ResponseEntity<>(owner, OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteOwner(@PathVariable long id) {
        try {
            ownerService.deleteOwner(id);
            return new ResponseEntity<>(OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        try {
            List<Owner> owners = ownerService.getAllOwners();
            return new ResponseEntity<>(owners, OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerById(id), OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<Owner> getOwnerByPetId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerById(id), OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }
}
