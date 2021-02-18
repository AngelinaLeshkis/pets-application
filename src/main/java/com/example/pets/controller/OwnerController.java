package com.example.pets.controller;

import com.example.pets.entity.Owner;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping(value = "/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public Owner saveOwner(@RequestBody Owner owner) {
        return ownerService.saveOwner(owner);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Owner> update(@RequestBody Owner owner, @PathVariable long id) {
        try {
            ownerService.updateOwner(owner, id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        try {
            ownerService.deleteOwner(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/owners")
    public List<Owner> getOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Owner> getById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<Owner> getByPetId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(ownerService.getOwnerById(id), HttpStatus.OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
