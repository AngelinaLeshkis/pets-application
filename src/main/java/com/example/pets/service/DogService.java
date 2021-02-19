package com.example.pets.service;

import com.example.pets.dto.CreateDogDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;

import java.util.List;

public interface DogService {

    DogDTO save(CreateDogDTO dog);

    DogDTO update(CreateDogDTO dog, long id);

    List<DogDTO> getAllByOwnerId(long id);

    Dog getById(long id);

}
