package com.example.pets.dto;

import com.example.pets.entity.Pet;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
public class OwnerDTO {

    private final long id;
    private final String name;
    private final List<Pet> pets;
}
