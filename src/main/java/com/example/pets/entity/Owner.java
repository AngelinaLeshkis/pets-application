package com.example.pets.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "owner")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets;
}
