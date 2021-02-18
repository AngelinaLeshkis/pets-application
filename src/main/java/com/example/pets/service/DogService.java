package com.example.pets.service;

import com.example.pets.dto.DogDTO;
import com.example.pets.entity.Dog;

import java.util.List;

public interface DogService {

    DogDTO save(Dog dog, long ownerId);

    DogDTO update(Dog dog, long ownerId, long id);

    List<DogDTO> getAllByOwnerId(long id);

    Dog getById(long id);

}
