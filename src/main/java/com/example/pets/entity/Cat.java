package com.example.pets.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Cat")
public class Cat extends Pet {

    @Column(name = "view")
    private String view;

    @Builder(builderMethodName = "catBuilder")
    public Cat(
            long id,
            String name,
            double age,
            Owner owner,
            String view) {
        super(id, name, age, owner);
        this.view = view;
    }
}
