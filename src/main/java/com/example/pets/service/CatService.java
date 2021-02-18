package com.example.pets.service;

import com.example.pets.dto.CatDTO;
import com.example.pets.entity.Cat;

import java.util.List;

public interface CatService {

    CatDTO save(Cat cat, long ownerId);

    CatDTO update(Cat cat, long ownerId, long id);

    List<CatDTO> getAllByOwnerId(long id);

    Cat getById(long id);

}
