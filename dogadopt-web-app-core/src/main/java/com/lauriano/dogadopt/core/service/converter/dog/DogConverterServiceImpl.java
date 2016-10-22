package com.lauriano.dogadopt.core.service.converter.dog;

import org.springframework.stereotype.Service;

import com.lauriano.dogadopt.core.service.converter.base.AbstractConverterService;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;
import com.lauriano.dogadopt.data.entity.dog.Dog;

@Service("dogConverterService")
public class DogConverterServiceImpl extends AbstractConverterService<Dog, DogContentItem>{

	@Override
	public Dog getEntity() {
		return new Dog();
	}

	@Override
	public DogContentItem getItem() {
		return new DogContentItem();
	}

}
