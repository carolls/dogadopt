package com.lauriano.dogadopt.data.repository.dog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lauriano.dogadopt.data.entity.dog.Dog;

@Repository("dogRepository")
public interface DogRepository extends CrudRepository<Dog, Long> {

}
