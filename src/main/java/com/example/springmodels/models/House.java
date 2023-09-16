package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min=1, message = "Message must contain min 1 symbol")
    @NotBlank(message = "Please provide information")
    private String Type;
    @NotBlank(message = "Please provide information")
    private String Street;
    @NotNull(message = "Please provide information")
    private int Number;
    @NotBlank(message = "Please provide information")
    private String Owner;
    @NotBlank(message = "Please provide information")
    private String ConstructionDate;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany
    @JoinTable(name = "habitant", joinColumns = @JoinColumn(name = "house_id"), inverseJoinColumns = @JoinColumn(name = "habitant_id"))
    private List<Habitant> habitants;

    public House(int id, String type, String street, int number, String owner, String constructionDate, Person person) {
        this.id = id;
        Type = type;
        Street = street;
        Number = number;
        Owner = owner;
        ConstructionDate = constructionDate;
        this.person = person;
    }

    public House() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getConstructionDate() {
        return ConstructionDate;
    }

    public void setConstructionDate(String constructionDate) {
        ConstructionDate = constructionDate;
    }

    public List<Habitant> getHabitantList() {
        return habitants;
    }

    public void setHabitantList(List<Habitant> habitants) {
        this.habitants = habitants;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
