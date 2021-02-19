package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.exception.OwnerNotFoundException;
import com.example.pets.service.CatService;
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

import static com.example.pets.mapper.PetMapper.toCatDto;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public ResponseEntity<CatDTO> save(@RequestBody Cat cat, @RequestParam(value = "ownerId") long ownerId) {
        return new ResponseEntity<>(catService.save(cat, ownerId), OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Cat cat, @PathVariable long id,
                                         @RequestParam(value = "ownerId") long ownerId) {
        try {
            catService.update(cat, ownerId, id);
            return new ResponseEntity<>(OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), NO_CONTENT);
        }
    }

    @GetMapping(value = "/cats/{id}")
    public ResponseEntity<List<CatDTO>> getAllByOwnerId(@PathVariable long id) {
        try {
            List<CatDTO> cats = catService.getAllByOwnerId(id);
            return new ResponseEntity<>(cats, OK);
        } catch (OwnerNotFoundException ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> getById(@PathVariable long id) {
        try {
            Cat catFromDB = catService.getById(id);
            return new ResponseEntity<>(toCatDto(catFromDB), OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(NO_CONTENT);
        }
    }
}
