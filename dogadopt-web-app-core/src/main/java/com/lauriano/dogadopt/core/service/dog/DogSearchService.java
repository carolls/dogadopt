package com.lauriano.dogadopt.core.service.dog;

import java.util.List;

import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

public interface DogSearchService {
	
	/**
	 * Get one dog by id
	 * 
	 * @param id
	 * @return
	 */
	public DogContentItem getById(Long id);
	
	/**
	 * Get all dogs
	 * 
	 * @return
	 */
	public List<DogContentItem> getAll();

}
