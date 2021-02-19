package com.example.pets.mapper;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Cat;
import com.example.pets.entity.Dog;
import com.example.pets.entity.Pet;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PetMapper {

    public static DogDTO toDogDto(Dog dog) {
        return DogDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .ownerId(dog.getOwner().getId())
                .breed(dog.getBreed())
                .build();
    }

    public static CatDTO toCatDto(Cat cat) {
        return CatDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .ownerId(cat.getOwner().getId())
                .view(cat.getView())
                .build();
    }

    public static PetDTO toDto(Pet pet) {
        return PetDTO.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .build();
    }
}
