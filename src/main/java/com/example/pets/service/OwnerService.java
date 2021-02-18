package com.example.pets.service;

import com.example.pets.entity.Owner;

import java.util.List;

public interface OwnerService {

    Owner saveOwner(Owner owner);

    void deleteOwner(long id);

    Owner updateOwner(Owner owner, long id);

    Owner getOwnerById(long id);

    List<Owner> getAllOwners();

    Owner getOwnerByPetId(long id);
}
