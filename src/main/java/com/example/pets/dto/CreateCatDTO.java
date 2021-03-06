package com.example.pets.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CreateCatDTO {

    private final String name;
    private final double age;
    private final long ownerId;
    private final String view;
}
