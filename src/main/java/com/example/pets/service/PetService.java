package com.example.pets.service;

import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Pet;

import java.util.List;

public interface PetService {

    void delete(long id);

    Pet getById(long id);

    List<Pet> getAll();

    List<PetDTO> getAllByOwnerId(long id);

}
