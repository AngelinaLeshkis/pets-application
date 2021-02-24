package com.example.pets.service;

import com.example.pets.dto.OwnerDTO;
import com.example.pets.entity.Owner;

import java.util.List;

public interface OwnerService {

    OwnerDTO save(Owner owner);

    void delete(long id);

    OwnerDTO update(Owner owner, long id);

    OwnerDTO getById(long id);

    List<OwnerDTO> getAll();

    OwnerDTO getByPetId(long id);
}
