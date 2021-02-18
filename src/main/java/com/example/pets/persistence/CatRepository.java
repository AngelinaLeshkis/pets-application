package com.example.pets.persistence;

import com.example.pets.entity.Cat;
import com.example.pets.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> getAllByOwnerId(long id);
}
