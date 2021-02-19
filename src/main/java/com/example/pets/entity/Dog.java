package com.example.pets.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Dog")
public class Dog extends Pet {

    @Column(name = "breed")
    private String breed;

    @Builder(builderMethodName = "dogBuilder")
    public Dog(
            long id,
            String name,
            double age,
            Owner owner,
            String breed) {
        super(id, name, age, owner);
        this.breed = breed;
    }
}
