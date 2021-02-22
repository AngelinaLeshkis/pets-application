package com.example.pets.persistence;

import com.example.pets.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> getAllByOwnerId(long id);
}
