package com.lauriano.dogadopt.core.service.dog.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

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
		try {
			DataModel model = new FileDataModel(new File("/path/to/dataset.csv"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(2, 3);
			for (RecommendedItem recommendation : recommendations) {
			  System.out.println(recommendation);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
