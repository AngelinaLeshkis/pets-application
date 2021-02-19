package com.example.pets.service;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.entity.Cat;

import java.util.List;

public interface CatService {

    CatDTO saveCat(CreateCatDTO cat);

    CatDTO update(CreateCatDTO cat, long id);

    List<CatDTO> getAllCatsByOwnerId(long id);

    Cat getCatById(long id);

}
