package com.example.springmodels.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "habitant")
public class Habitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*@ManyToMany(mappedBy = "habitants")
    private List<House> houseList;*/
}
