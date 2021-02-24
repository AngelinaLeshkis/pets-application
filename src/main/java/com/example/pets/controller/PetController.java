package com.example.pets.controller;

import com.example.pets.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping(value = "/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        petService.delete(id);
        return new ResponseEntity<>(OK);
    }

}
