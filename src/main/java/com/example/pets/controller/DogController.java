package com.example.pets.controller;

import com.example.pets.dto.CreateDogDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.service.DogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.pets.mapper.PetMapper.toDogDto;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping(value = "/dogs")
@RequiredArgsConstructor
public class DogController {

    private final DogService dogService;

    @PostMapping
    public ResponseEntity<DogDTO> save(@RequestBody CreateDogDTO dog) {
        DogDTO savedDog = dogService.save(dog);
        return new ResponseEntity<>(savedDog, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DogDTO> update(@RequestBody CreateDogDTO dog,
                                         @PathVariable long id) {
        DogDTO savedDog = dogService.update(dog, id);
        return new ResponseEntity<>(savedDog, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DogDTO> getById(@PathVariable long id) {
        DogDTO dogFromDB = toDogDto(dogService.getById(id));
        return new ResponseEntity<>(dogFromDB, OK);
    }
}
