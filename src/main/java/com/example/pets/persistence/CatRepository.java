package com.example.pets.persistence;

import com.example.pets.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> getAllByOwnerId(long id);
}
