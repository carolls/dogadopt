package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import com.lauriano.dogadopt.core.service.converter.base.ConverterService;
import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;
import com.lauriano.dogadopt.data.entity.dog.Dog;

@Service("dogSearchService")
public class DogSearchServiceImpl implements DogSearchService {

	@Autowired
	@Qualifier("dogRepository")
	protected CrudRepository<Dog, Long> dogRepository;

	@Autowired
	@Qualifier("dogConverterService")
	protected ConverterService<Dog, DogContentItem> dogConverterService;

	
	@Override
	public DogContentItem getById(Long id) {
		final Dog entity = dogRepository.findOne(id);
		return dogConverterService.toItem(entity);
	}

	@Override
	public List<DogContentItem> getAll() {
		final List<Dog> entities = Lists.newArrayList(dogRepository.findAll());
		return dogConverterService.toListItem(entities);
	}

}
