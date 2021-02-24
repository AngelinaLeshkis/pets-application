package com.example.pets.service;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.entity.Cat;

import java.util.List;

public interface CatService {

    CatDTO save(CreateCatDTO cat);

    CatDTO update(CreateCatDTO cat, long id);

    List<CatDTO> getAllByOwnerId(long id);

    Cat getById(long id);

}
