package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.service.CatService;
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

import static com.example.pets.mapper.PetMapper.toCatDto;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<CatDTO> save(@RequestBody CreateCatDTO cat) {
        CatDTO savedCat = catService.save(cat);
        return new ResponseEntity<>(savedCat, CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CatDTO> update(@RequestBody CreateCatDTO cat,
                                         @PathVariable long id) {
        CatDTO savedCat = catService.update(cat, id);
        return new ResponseEntity<>(savedCat, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> getById(@PathVariable long id) {
        CatDTO catFromDB = toCatDto(catService.getById(id));
        return new ResponseEntity<>(catFromDB, OK);
    }
}
