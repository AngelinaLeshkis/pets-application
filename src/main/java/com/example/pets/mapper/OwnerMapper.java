package com.example.pets.mapper;

import com.example.pets.dto.OwnerDTO;
import com.example.pets.entity.Owner;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class OwnerMapper {

    public static OwnerDTO toOwnerDTO(Owner owner) {
        return OwnerDTO.builder()
                .id(owner.getId())
                .name(owner.getName())
                .pets(owner.getPets())
                .build();
    }
}
