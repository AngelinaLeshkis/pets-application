package com.example.pets.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Cat")
public class Cat extends Pet{

    @Column(name = "view")
    private String view;
}
