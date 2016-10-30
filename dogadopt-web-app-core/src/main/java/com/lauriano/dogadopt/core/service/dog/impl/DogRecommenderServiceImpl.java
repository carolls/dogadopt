package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lauriano.dogadopt.core.service.dog.DogSearchService;
import com.lauriano.dogadopt.core.service.dog.DogRecommenderService;
import com.lauriano.dogadopt.data.contentitem.dog.DogFormContentItem;
import com.lauriano.dogadopt.data.contentitem.dog.DogContentItem;

@Service("dogRecommenderService")
public class DogRecommenderServiceImpl implements DogRecommenderService {
	private static final Logger log = LoggerFactory.getLogger(DogRecommenderServiceImpl.class);
	@Autowired
	@Qualifier("dogSearchService")
	protected DogSearchService dogSearchService;

	@Override
	public List<DogContentItem> generateRecomendation(DogFormContentItem dogForm) {
		//  A partir del custionario, obtener un listado de perros recomndados
		final List<DogContentItem> result = dogSearchService.getAll();
		log.info(beantoString(dogForm));
		DogContentItem user = transformFormInDog(dogForm);
		try {
			DataModel model = transformDogsInAModel(user,result);
			//DataModel model = new FileDataModel(new File("data.txt"));
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
			UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
			List<RecommendedItem> recommendations = recommender.recommend(0L, 3);
			for (RecommendedItem recommendation : recommendations) {
				//recupera Dogs con los IDs 
				log.info(recommendation.toString());
			}
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static DogContentItem transformFormInDog(DogFormContentItem dogForm){
		DogContentItem dog = new DogContentItem();
		
		BeanUtils.copyProperties(dog, dogForm);
		dog.setId(0L);
		if (dogForm.isIndependent2()){
			dog.setIndependent(1);
		}
		dog.setSpecialNeeds((dogForm.getSpecialNeeds()+dogForm.getSpecialNeeds2())%4);
		dog.setSociable((dogForm.getSociable()+dogForm.getSociable2())%4);
		return dog;
	}
	public static GenericDataModel transformDogsInAModel(DogContentItem user,List<DogContentItem> list){
		FastByIDMap<PreferenceArray> preferences =
				 new FastByIDMap<PreferenceArray>();
		//add usuario
		PreferenceArray prefsForUser = new GenericUserPreferenceArray(15);
		prefsForUser.setUserID(0, user.getId());
		prefsForUser.setItemID(0, 101L);
		prefsForUser.setValue(0, user.getHair());
		prefsForUser.setItemID(1, 102L);
		prefsForUser.setValue(1, user.getColor());
		prefsForUser.setItemID(2, 103L);
		prefsForUser.setValue(2, user.getSize());
		prefsForUser.setItemID(3, 104L);
		prefsForUser.setValue(3, user.getPlayful());
		prefsForUser.setItemID(4, 105L);
		prefsForUser.setValue(4, user.getSociable());
		prefsForUser.setItemID(5, 106L);
		prefsForUser.setValue(5, user.getIndependent());
		prefsForUser.setItemID(6, 107L);
		prefsForUser.setValue(6, user.getActive());
		prefsForUser.setItemID(7, 108L);
		prefsForUser.setValue(7, user.getAffectionate());
		prefsForUser.setItemID(8, 109L);
		prefsForUser.setValue(8, user.getSpecialNeeds());
		prefsForUser.setItemID(9, 110L);
		prefsForUser.setValue(9, user.getNoiseTolerance());
		prefsForUser.setItemID(10, 111L);
		prefsForUser.setValue(10, user.getExpensive());
		prefsForUser.setItemID(10, 111L);
		preferences.put(0L, prefsForUser);
		//add all dogs
		for (int i=0;i<list.size();i++) {
			PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(15);
			DogContentItem dog= list.get(i);
			prefsForUser1.setUserID(i+1, dog.getId());
			prefsForUser1.setItemID(0, 101L);
			prefsForUser1.setValue(0, dog.getHair());
			prefsForUser1.setItemID(1, 102L);
			prefsForUser1.setValue(1, dog.getColor());
			prefsForUser1.setItemID(2, 103L);
			prefsForUser1.setValue(2, dog.getSize());
			prefsForUser1.setItemID(3, 104L);
			prefsForUser1.setValue(3, dog.getPlayful());
			prefsForUser1.setItemID(4, 105L);
			prefsForUser1.setValue(4, dog.getSociable());
			prefsForUser1.setItemID(5, 106L);
			prefsForUser1.setValue(5, dog.getIndependent());
			prefsForUser1.setItemID(6, 107L);
			prefsForUser1.setValue(6, dog.getActive());
			prefsForUser1.setItemID(7, 108L);
			prefsForUser1.setValue(7, dog.getAffectionate());
			prefsForUser1.setItemID(8, 109L);
			prefsForUser1.setValue(8, dog.getSpecialNeeds());
			prefsForUser1.setItemID(9, 110L);
			prefsForUser1.setValue(9, dog.getNoiseTolerance());
			prefsForUser1.setItemID(10, 111L);
			prefsForUser1.setValue(10, dog.getExpensive());
			prefsForUser1.setItemID(10, 111L);
			preferences.put(i+1, prefsForUser1);
		}
		return  new GenericDataModel(preferences);
	}
	 public String beantoString(Object obj) {
		   return ToStringBuilder.reflectionToString(obj);
		 }

}
