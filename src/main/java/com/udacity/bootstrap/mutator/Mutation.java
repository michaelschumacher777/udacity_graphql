package com.udacity.bootstrap.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.bootstrap.entity.Dog;
import com.udacity.bootstrap.exception.BreedNotFoundException;
import com.udacity.bootstrap.exception.DogNotDeletedException;
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

    public boolean deleteDog(Long id) {
        dogRepository.deleteById(id);
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            throw new DogNotDeletedException("Dog not deleted.", id);
        }
        return true;
    }

    public Dog updateDog(String name, String breed, String origin, Long id) {
        Dog updatedDog = dogRepository.updateDog(name, breed, origin, id);
        return updatedDog;
    }

}
