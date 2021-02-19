package com.example.pets.controller;

import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.exception.PetNotFoundException;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.pets.mapper.PetMapper.toDogDto;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/dogs")
@AllArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping
    public ResponseEntity<DogDTO> save(@RequestBody Dog dog, @RequestParam(value = "ownerId") long ownerId) {
        return new ResponseEntity<>(dogService.save(dog, ownerId), CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Dog dog, @PathVariable long id,
                                         @RequestParam(value = "ownerId") long ownerId) {
        try {
            dogService.update(dog, ownerId, id);
            return new ResponseEntity<>(OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<DogDTO>> getAllByOwnerId(@PathVariable long id) {
        try {
            List<DogDTO> dogs = dogService.getAllByOwnerId(id);
            return new ResponseEntity<>(dogs, OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/dog/{id}")
    public ResponseEntity<DogDTO> getById(@PathVariable long id) {
        try {
            Dog dogFromDB = dogService.getById(id);
            return new ResponseEntity<>(toDogDto(dogFromDB), OK);
        } catch (PetNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }
}
