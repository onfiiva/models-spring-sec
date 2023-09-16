package com.example.springmodels.repos;

import com.example.springmodels.models.Pet;
import org.springframework.data.repository.CrudRepository;


public interface PetRepository extends CrudRepository<Pet, Long> {
}
