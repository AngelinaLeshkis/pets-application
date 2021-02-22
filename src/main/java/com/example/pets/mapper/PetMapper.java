package com.example.pets.mapper;

import com.example.pets.dto.CatDTO;
import com.example.pets.dto.CreateCatDTO;
import com.example.pets.dto.CreateDogDTO;
import com.example.pets.dto.DogDTO;
import com.example.pets.dto.PetDTO;
import com.example.pets.entity.Cat;
import com.example.pets.entity.Dog;
import com.example.pets.entity.Owner;
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

    public static Cat toCat(CreateCatDTO catDTO, Owner owner) {
        return Cat.catBuilder()
                .name(catDTO.getName())
                .age(catDTO.getAge())
                .owner(owner)
                .view(catDTO.getView())
                .build();
    }

    public static Dog toDog(CreateDogDTO dogDTO, Owner owner) {
        return Dog.dogBuilder()
                .name(dogDTO.getName())
                .age(dogDTO.getAge())
                .owner(owner)
                .breed(dogDTO.getBreed())
                .build();
    }

    public static Cat updateCat(CreateCatDTO catDTO, long id) {
        return Cat.catBuilder()
                .id(id)
                .name(catDTO.getName())
                .age(catDTO.getAge())
                .view(catDTO.getView())
                .build();
    }

    public static Dog updateDog(CreateDogDTO dogDTO, long id) {
        return Dog.dogBuilder()
                .id(id)
                .name(dogDTO.getName())
                .age(dogDTO.getAge())
                .breed(dogDTO.getBreed())
                .build();
    }
}
