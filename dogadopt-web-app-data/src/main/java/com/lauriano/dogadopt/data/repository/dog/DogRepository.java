package com.lauriano.dogadopt.data.repository.dog;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lauriano.dogadopt.data.entity.dog.Dog;

@Repository("dogRepository")
public interface DogRepository extends CrudRepository<Dog, Long> {
	
	@Query("from #{#entityName} where id in :ids" )
	List<Dog> findByIds(@Param("ids") List<Long> ids);
	
	@Query("from #{#entityName} where childrens=:childrens and otherDogs=:otherDogs and anyCats=:anyCats and alergies=:alergies")
	List<Dog> findAllByBooleanFilters(
			@Param("childrens") final boolean childrens,
			@Param("otherDogs") final boolean otherDogs,
			@Param("anyCats") final boolean anyCats,
			@Param("alergies") final boolean alergies);
	
}
