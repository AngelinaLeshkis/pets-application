package com.example.pets.persistence;

import com.example.pets.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    List<Dog> getAllByOwnerId(long id);
}
