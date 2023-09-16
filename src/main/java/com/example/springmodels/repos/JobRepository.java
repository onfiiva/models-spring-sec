package com.example.springmodels.repos;

import com.example.springmodels.models.Job;
import org.springframework.data.repository.CrudRepository;


public interface JobRepository extends CrudRepository<Job, Long> {
}
