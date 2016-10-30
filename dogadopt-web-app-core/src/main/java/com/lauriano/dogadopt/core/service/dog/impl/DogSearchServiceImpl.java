package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import com.lauriano.dogadopt.core.service.converter.base.ConverterService;
import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;
import com.lauriano.dogadopt.data.entity.dog.Dog;
import com.lauriano.dogadopt.data.repository.dog.DogRepository;

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
		final boolean hasChildrens = childrens != null ? childrens : false;
		final boolean hasOtherDogs = otherDogs != null ? otherDogs : false;
		final boolean hasAnyCats = anyCats != null ? anyCats : false;
		final boolean hasAlergies = alergies != null ? alergies : false;		
		final List<Dog> entities = dogRepository.findAllByBooleanFilters(hasChildrens, hasOtherDogs, hasAnyCats, hasAlergies);
		return dogConverterService.toListItem(entities);
	}

}
