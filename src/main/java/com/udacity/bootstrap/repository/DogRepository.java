package com.udacity.bootstrap.repository;

import com.udacity.bootstrap.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {
    @Query("select d.id, d.breed from Dog d where d.id=:id")
    String findBreedById(Long id);

    @Query("select d.id, d.breed from Dog d")
    List<String> findAllBreeds();

    @Query("select d.id, d.name from Dog d")
    List<String> findAllNames();

    @Query("update Dog set name = :name, breed = :breed, origin = :origin where id = :id")
    Dog updateDog(String name, String breed, String origin, Long id);
}
