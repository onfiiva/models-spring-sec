package com.example.springmodels.repos;

import com.example.springmodels.models.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.naming.directory.SearchResult;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UniversalRepositoryImpl implements UniversalRepository{
    private final HouseRepository houseRepository;
    private final JobRepository jobRepository;
    private final PersonRepository personRepository;
    private final PetRepository petRepository;
    private final TransportRepository transportRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UniversalRepositoryImpl(HouseRepository houseRepository,
                                   JobRepository jobRepository,
                                   PersonRepository personRepository,
                                   PetRepository petRepository,
                                   TransportRepository transportRepository, Map<String, org.springframework.data.repository.Repository<?, ?>> repositoryMap ) {
        this.houseRepository = houseRepository;
        this.jobRepository = jobRepository;
        this.personRepository = personRepository;
        this.petRepository = petRepository;
        this.transportRepository = transportRepository;
        this.repositoryMap = repositoryMap;
    }
    private final Map<String, org.springframework.data.repository.Repository<?, ?>> repositoryMap;
    @Override
    public Optional<?> findEntityById(String modelName, Long id) {
        return switch (modelName) {
            case "Comment" -> houseRepository.findById(id);
            case "Friendship" -> jobRepository.findById(id);
            case "Message" -> personRepository.findById(id);
            case "Post" -> petRepository.findById(id);
            case "User" -> transportRepository.findById(id);
            default -> Optional.empty();
        };
    }

    @Override
    public void saveEntity(String modelName, Object entity) {
        switch (modelName) {
            case "Comment" -> houseRepository.save((House) entity);
            case "Friendship" -> jobRepository.save((Job) entity);
            case "Message" -> personRepository.save((Person) entity);
            case "Post" -> petRepository.save((Pet) entity);
            case "User" -> transportRepository.save((Transport) entity);
            default -> throw new IllegalArgumentException("Invalid model name");
        }
    }

    @Override
    public <T> void updateEntity(T entity, Map<String, String> fieldValues) {
        Class<?> entityClass = entity.getClass();

        for (Field field : entityClass.getDeclaredFields()) {
            String fieldName = field.getName();

            if (fieldValues.containsKey(fieldName)) {
                String fieldValue = fieldValues.get(fieldName);

                try {
                    String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class<?> fieldClass = field.getType();
                    Class<?>[] paramTypes = new Class<?>[] { fieldClass };
                    java.lang.reflect.Method setterMethod = entityClass.getMethod(setterMethodName, paramTypes);

                    Object convertedValue = convertStringToType(fieldClass, fieldValue);

                    setterMethod.invoke(entity, convertedValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<SearchResult> searchEntities(String modelName, String fieldName, String fieldValue) throws ClassNotFoundException {
        String jpql = "SELECT e FROM " + modelName + " e WHERE e." + fieldName + " = :fieldValue";
        Query query = entityManager.createQuery(jpql, Class.forName("com.example.modelsspring.models." + modelName));
        query.setParameter("fieldValue", fieldValue);
        return query.getResultList();
    }
    private Class<?> getEntityClassByName(String modelName) {
        try {
            return Class.forName("com.example.modelsspring.models." + modelName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Invalid model name: " + modelName);
        }
    }
    @Override
    public void deleteEntityById(String modelName, Long id) {
        /*JpaRepository<?, Long> repository = (JpaRepository<?, Long>) repositoryMap.get(modelName);

        if (repository != null) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Invalid model name");
        }*/
        switch (modelName) {
            case "Comment" -> houseRepository.deleteById(id);
            case "Friendship" -> jobRepository.deleteById(id);
            case "Message" -> personRepository.deleteById(id);
            case "Post" -> petRepository.deleteById(id);
            case "User" -> transportRepository.deleteById(id);
            default -> throw new IllegalArgumentException("Invalid model name");
        }
    }

    private Object convertStringToType(Class<?> targetType, String value) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(value);
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(value);
        }
        return null;
    }
}
