package com.example.pets.controller;

import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;
import com.example.pets.exception.PetNotFoundException;
import com.example.pets.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping(value = "/dog")
@AllArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping
    public DogDTO save(@RequestBody Dog dog, @RequestParam(value = "ownerId") long ownerId) {
        return dogService.save(dog, ownerId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Dog dog, @PathVariable long id,
                                         @RequestParam(value = "ownerId") long ownerId) {
        try {
            dogService.update(dog, ownerId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/dogs/{id}")
    public List<DogDTO> getAllByOwnerId(@PathVariable long id) {
        return dogService.getAllByOwnerId(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DogDTO> getById(@PathVariable long id) {
        try {
            Dog dogFromDB = dogService.getById(id);
            return new ResponseEntity<>(toDogDto(dogFromDB), HttpStatus.OK);
        } catch (PetNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
