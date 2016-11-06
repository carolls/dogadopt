package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.lauriano.dogadopt.core.service.converter.base.ConverterService;
import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;
import com.lauriano.dogadopt.data.entity.dog.Dog;
import com.lauriano.dogadopt.data.entity.dog.QDog;
import com.lauriano.dogadopt.data.repository.dog.DogRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

@Service("dogSearchService")
public class DogSearchServiceImpl implements DogSearchService {

	@Autowired
	@Qualifier("dogRepository")
	protected DogRepository dogRepository;

	@Autowired
	@Qualifier("dogConverterService")
	protected ConverterService<Dog, DogContentItem> dogConverterService;

	
	@Override
	public DogContentItem getById(final Long id) {
		final Dog entity = dogRepository.findOne(id);
		return dogConverterService.toItem(entity);
	}

	@Override
	public List<DogContentItem> getAll() {
		final List<Dog> entities = Lists.newArrayList(dogRepository.findAll());
		return dogConverterService.toListItem(entities);
	}
	
	@Override
	public List<DogContentItem> getByIds(long[] ids) {
		final Long[] idsObjects = ArrayUtils.toObject(ids);
		final List<Long> idsList = Arrays.asList(idsObjects);
		final List<Dog> entities = dogRepository.findByIds(idsList);
		return dogConverterService.toListItem(entities);
	}	


	
	@Override
	public List<DogContentItem> getAllByBooleanFilters(final Boolean childrens, final Boolean otherDogs, final Boolean anyCats, final Boolean alergies) {
		final boolean hasChildrens = (childrens != null && childrens) ? true : false;
		final boolean hasOtherDogs =(otherDogs != null && otherDogs) ? true : false;
		final boolean hasAnyCats =(anyCats != null && anyCats) ? true : false;
		final boolean hasAlergies =(alergies != null && alergies) ? true : false;	

		final QDog dog = QDog.dog;
		final BooleanBuilder where = new BooleanBuilder();
		
		if(hasChildrens) {
			where.and(dog.childrens.eq(hasChildrens));
		}
		if(hasOtherDogs) {
			where.and(dog.otherDogs.eq(hasOtherDogs));
		}
		if(hasAnyCats) {
			where.and(dog.anyCats.eq(hasAnyCats));
		}
		if(hasAlergies) {
			where.and(dog.alergies.eq(hasAlergies));
		}
		
		final Predicate predicate = where.getValue();	
		final Iterable<Dog> entities = dogRepository.findAll(predicate);
		
		return dogConverterService.toListItem(entities);
	}

}
