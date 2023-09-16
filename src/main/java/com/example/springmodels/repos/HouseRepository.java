package com.example.springmodels.repos;

import com.example.springmodels.models.House;
import org.springframework.data.repository.CrudRepository;


public interface HouseRepository extends CrudRepository<House, Long> {
}
