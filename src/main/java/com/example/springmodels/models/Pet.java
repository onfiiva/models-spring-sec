package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Please provide information")
    private String Name;
    @NotBlank(message = "Please provide information")
    private String Breed;
    @NotBlank(message = "Please provide information")
    private String Type;
    @NotNull(message = "Please provide information")
    private int Age;
    @NotBlank(message = "Please provide information")
    private String Gender;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Pet(int id, String name, String breed, String type, int age, String gender, Person person) {
        this.id = id;
        Name = name;
        Breed = breed;
        Type = type;
        Age = age;
        Gender = gender;
        this.person = person;
    }

    public Pet() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
