package com.lauriano.dogadopt.core.service.converter.base;

import java.util.List;

public interface ConverterService<ENTITY, ITEM> {
	
	/**
	 * Given an entity returns an item
	 * 
	 * @param entity
	 * @return
	 */
	public ITEM toItem(ENTITY entity);
	
	/**
	 * Given a list of entities returns a list of items
	 * 
	 * @param entities
	 * @return
	 */
	public List<ITEM> toListItem(Iterable<ENTITY> entities);

	/**
	 * Given a item return an entity
	 * 
	 * @param Item
	 * @return
	 */
	public ENTITY toEntity(ITEM item);
	
	/**
	 * Given a list of items returns a list of entities
	 * @param items
	 * @return
	 */
	public List<ENTITY> toListEntity(List<ITEM> items);

}
