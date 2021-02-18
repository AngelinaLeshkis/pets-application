package com.example.pets.persistence;

import com.example.pets.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findPetsByOwnerId(long id);

    Long findOwnerIdById(long id);
}
