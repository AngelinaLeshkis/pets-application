package com.example.pets.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class PetDTO {

    private final long id;
    private final String name;
    private final double age;
    private final long ownerId;
}
