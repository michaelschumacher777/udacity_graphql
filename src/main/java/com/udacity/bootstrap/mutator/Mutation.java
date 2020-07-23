package com.udacity.bootstrap.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.exception.BreedNotFoundException;
import com.udacity.bootstrap.exception.DogNotFoundException;
import com.udacity.bootstrap.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog newDog(String name, String breed, String origin) {
        Dog dog = new Dog(name, breed, origin);
        dogRepository.save(dog);
        return dog;
    }

    public boolean deleteDogBreed(String breed) {
        Iterable<Dog> allDogs = dogRepository.findAll();
        boolean deleted = false;
        for (Dog dog : allDogs) {
            if (dog.getBreed().equals(breed)) {
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        
        if (! deleted) {
            throw new BreedNotFoundException("Dog not found", breed);
        }
        return true;
    }

    public Dog updateDogName(String name, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(name);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
