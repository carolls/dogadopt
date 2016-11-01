package com.lauriano.dogadopt.data.repository.dog;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lauriano.dogadopt.data.entity.dog.Dog;

@Repository("dogRepository")
public interface DogRepository extends CrudRepository<Dog, Long>, QueryDslPredicateExecutor<Dog> {
	
	@Query("from #{#entityName} where id in :ids" )
	List<Dog> findByIds(@Param("ids") List<Long> ids);
	
	@Query("from #{#entityName} where childrens=:childrens and otherDogs=:otherDogs and anyCats=:anyCats and alergies=:alergies")
	List<Dog> findAllByBooleanFilters(
			@Param("childrens") final Boolean childrens,
			@Param("otherDogs") final Boolean otherDogs,
			@Param("anyCats") final Boolean anyCats,
			@Param("alergies") final Boolean alergies);
	
}
