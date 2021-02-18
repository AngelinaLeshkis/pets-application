package com.example.pets.service;

import com.example.pets.entity.Pet;
import com.example.pets.dto.PetDTO;

import java.util.List;

public interface PetService {

    void deletePet(long id);

    Pet getPetById(long id);

    List<Pet> getAllPets();

    List<PetDTO> getPetsByOwnerId(long id);

}
