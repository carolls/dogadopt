package com.lauriano.dogadopt.core.service.converter.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;

public abstract class AbstractConverterService<ENTITY, ITEM> implements ConverterService<ENTITY, ITEM> {
	
	public abstract ENTITY getEntity();
	public abstract ITEM getItem();
	

	@Override
	public ITEM toItem(ENTITY entity) {
		ITEM item = getItem();
		BeanUtils.copyProperties(entity, item);
		customToItem(entity, item);
		return item;
	}
	
	@Override
	public List<ITEM> toListItem(Iterable<ENTITY> entities){
		List<ITEM> list = new ArrayList<ITEM>();
		Iterator<ENTITY> iterator = entities.iterator();
		while (iterator.hasNext()) {
			list.add(toItem(iterator.next()));
		}
		return Collections.unmodifiableList(list);
	}

	@Override
	public ENTITY toEntity(ITEM item) {
		ENTITY entity = getEntity();
		BeanUtils.copyProperties(item, entity);
		customToEntity(item,entity);
		return entity;
	}
	
	@Override
	public List<ENTITY> toListEntity(List<ITEM> items){
		List<ENTITY> list = new ArrayList<ENTITY>(items.size());
		for (ITEM out : items) {
			list.add(toEntity(out));
		}
		return Collections.unmodifiableList(list);
	}
	
	/**
	 * Override this method to add extra item conversion
	 * 
	 * @param entity
	 * @param item
	 */
	protected void customToItem(ENTITY entity, ITEM item) {
		// Default empty implementation
	}

	/**
	 * Override this method to add extra entity conversion
	 * 
	 * @param item
	 * @param entity
	 */
	protected void customToEntity(ITEM item, ENTITY entity) {
		// Default empty implementation
	}

}
