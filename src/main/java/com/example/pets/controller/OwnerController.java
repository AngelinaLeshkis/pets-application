package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.dto.OwnerDTO;
import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Owner;
import com.example.pets.service.CatService;
import com.example.pets.service.DogService;
import com.example.pets.service.OwnerService;
import com.example.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping(value = "/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final DogService dogService;
    private final CatService catService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<OwnerDTO> save(@RequestBody Owner owner) {
        OwnerDTO savedOwner = ownerService.save((owner));
        return new ResponseEntity<>(savedOwner, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OwnerDTO> update(@RequestBody Owner owner,
                                           @PathVariable long id) {
        OwnerDTO updatedOwner = ownerService.update(owner, id);
        return new ResponseEntity<>(updatedOwner, OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        ownerService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> getAll() {
        List<OwnerDTO> owners = ownerService.getAll();
        return new ResponseEntity<>(owners, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OwnerDTO> getById(@PathVariable long id) {
        OwnerDTO savedOwner = ownerService.getById(id);
        return new ResponseEntity<>(savedOwner, OK);
    }

    @GetMapping(value = "/pet/{id}")
    public ResponseEntity<OwnerDTO> getByPetId(@PathVariable long id) {
        OwnerDTO ownerFromDB = ownerService.getByPetId(id);
        return new ResponseEntity<>(ownerFromDB, OK);
    }

    @GetMapping(value = "/{id}/dogs")
    public ResponseEntity<List<DogDTO>> getDogsById(@PathVariable long id) {
        List<DogDTO> dogs = dogService.getAllByOwnerId(id);
        return new ResponseEntity<>(dogs, OK);

    }

    @GetMapping(value = "/{id}/cats")
    public ResponseEntity<List<CatDTO>> getCatsById(@PathVariable long id) {
        List<CatDTO> cats = catService.getAllByOwnerId(id);
        return new ResponseEntity<>(cats, OK);
    }

    @GetMapping(value = "/{id}/pets")
    public ResponseEntity<List<PetDTO>> getPetsById(@PathVariable long id) {
        List<PetDTO> pets = petService.getAllByOwnerId(id);
        return new ResponseEntity<>(pets, OK);
    }
}
