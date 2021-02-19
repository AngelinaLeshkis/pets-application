package com.example.pets.entity;

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
}
