package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Please provide information")
    private String Surname;
    @NotBlank(message = "Please provide information")
    private String Name;
    @NotBlank(message = "Please provide information")
    private String Patronymic;
    @NotNull(message = "Please provide information")
    private int Age;

    @OneToOne(optional = true, mappedBy = "person")
    private House house;

    @OneToOne(optional = true, mappedBy = "person")
    private Job job;

    @OneToMany(mappedBy = "person")
    private List<Pet> pets;

    @OneToMany(mappedBy = "person")
    private List<Transport> transports;

    public Person(int id, String surname, String name, String patronymic, int age, House house, Job job) {
        this.id = id;
        Surname = surname;
        Name = name;
        Patronymic = patronymic;
        Age = age;
        this.house = house;
        this.job = job;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPatronymic() {
        return Patronymic;
    }

    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }
}
