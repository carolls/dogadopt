package com.lauriano.dogadopt.core.service.dog;

import java.util.List;

import com.lauriano.dogadopt.data.contentitem.dog.DogFormContentItem;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

public interface DogRecommenderService {
	
	/**
	 * Given a dog form returns a list of recommended dogs to be adopted
	 * 
	 * @param dogForm
	 * @return
	 */
	public List<DogContentItem> generateRecomendation(DogFormContentItem dogForm);

}
