package com.udacity.bootstrap.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.exception.DogNotFoundException;
import com.udacity.bootstrap.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        System.out.println(">>> findAllDogs");
        Iterable<Dog> allDogs = dogRepository.findAll();
        int count = 0;
        for (Dog dog : allDogs) {
            System.out.println("Dog name: " + dog.getName());
            count++;
        }
        System.out.println("<<< findAllDogs["+count+"]");
        return allDogs;
    }

    public Dog  findDogById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}
