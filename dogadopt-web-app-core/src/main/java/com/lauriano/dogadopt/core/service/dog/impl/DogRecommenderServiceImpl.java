package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.core.service.dog.DogRecommenderService;
import com.lauriano.dogadopt.data.contentitem.dog.DogFormContentItem;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

@Service("dogRecommenderService")
public class DogRecommenderServiceImpl implements DogRecommenderService {
	
	@Autowired
	@Qualifier("dogSearchService")
	protected DogSearchService dogSearchService;

	@Override
	public List<DogContentItem> generateRecomendation(DogFormContentItem dogForm) {
		// TODO A partir del custionario, obtener un listado de perros recomndados
		final List<DogContentItem> result = dogSearchService.getAll();
		return result;
	}

}
