package com.example.pets.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
