package com.example.pets.controller;

import com.example.pets.dto.CatDTO;
import com.example.pets.entity.Cat;
import com.example.pets.service.CatService;
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

import static com.example.pets.mapper.PetMapper.toCatDto;

@RestController
@RequestMapping(value = "/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping
    public CatDTO save(@RequestBody Cat cat, @RequestParam(value = "ownerId") long ownerId) {
        return catService.save(cat, ownerId);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@RequestBody Cat cat, @PathVariable long id,
                                         @RequestParam(value = "ownerId") long ownerId) {
        try {
            catService.update(cat, ownerId, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/cats/{id}")
    public List<CatDTO> getAllByOwnerId(@PathVariable long id) {
        return catService.getAllByOwnerId(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> getById(@PathVariable long id) {
        try {
            Cat catFromDB = catService.getById(id);
            return new ResponseEntity<>(toCatDto(catFromDB), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
