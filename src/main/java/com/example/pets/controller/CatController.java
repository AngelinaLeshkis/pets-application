package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

import static com.example.pets.mapper.PetMapper.toCatDto;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<CatDTO> saveCat(@RequestBody CreateCatDTO cat) {
        return new ResponseEntity<>(catService.saveCat(cat), OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateCat(@RequestBody CreateCatDTO cat,
                                            @PathVariable long id) {
        try {
            catService.update(cat, id);
            return new ResponseEntity<>(OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> getCatById(@PathVariable long id) {
        try {
            Cat catFromDB = catService.getCatById(id);
            return new ResponseEntity<>(toCatDto(catFromDB), OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }
}
