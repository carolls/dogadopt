package com.lauriano.dogadopt.core.service.dog;

import java.util.List;
import java.util.Set;

import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

public interface DogSearchService {
	
	/**
	 * Get one dog by id
	 * 
	 * @param id
	 * @return
	 */
	public DogContentItem getById(final Long id);
		
	/**
	 * Get all dogs
	 * 
	 * @return
	 */
	public List<DogContentItem> getAll();
	
	/**
	 * Get a list of dogs by ids
	 * 
	 * @param ids
	 * @return
	 */
	public List<DogContentItem> getByIds(final long[] ids);
	

	
	/**
	 * Get all dogs by boolean filters
	 * 
	 * @return
	 */
	public List<DogContentItem> getAllByBooleanFilters(final Boolean childrens, final Boolean otherDogs, final Boolean anyCats, final Boolean alergies);

}
