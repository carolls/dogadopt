package com.lauriano.dogadopt.core.service.dog.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
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
	
	@Autowired
	@Qualifier("dogAdoptEmail")
	protected SendEmailAPI sendEmailAPI;

	@Override
	public List<DogContentItem> generateRecomendation(DogFormContentItem dogForm) {
		//  A partir del custionario, obtener un listado de perros recomndados
		//  Filtrar por valores discriminatorios
		final Boolean childrens = dogForm.isChildrens();
		final Boolean otherDogs = dogForm.isOtherDogs();
		final Boolean anyCats = dogForm.isAnyCats();
		final Boolean alergies = dogForm.isAlergies();
		final List<DogContentItem> result = dogSearchService.getAllByBooleanFilters(childrens, otherDogs, anyCats, alergies);
	//	log.info(beantoString(dogForm));
		DogContentItem user = transformFormInDog(dogForm);
	//	log.info(beantoString(user));
		try {
			DataModel model = transformDogsInAModel(user,result);
		//	log.info(beantoString(model));
			//DataModel model = new FileDataModel(new File("data.txt"));
			UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood =
				      new NearestNUserNeighborhood(10, userSimilarity, model);
			long[] vecinos=neighborhood.getUserNeighborhood(0L);
			//log.info("---------neighborhood-------------");
			//log.info(beantoString(vecinos));
			if (vecinos.length>0){
				final List<DogContentItem> resultFiltered = dogSearchService.getByIds(vecinos);
				/*for (int i=0;i<vecinos.length;i++){
					((DogContentItem) resultFiltered.get(i)).setSimilarity((userSimilarity.userSimilarity(0L, vecinos[i]))*100);
					log.info("---------similaritud-------------");
					log.info(vecinos[i]+"");
					log.info(beantoString(userSimilarity.userSimilarity(0L, vecinos[i])));
				}*/
				
				for (DogContentItem dogItem : resultFiltered){
					Long dogId = dogItem.getId();
					Long elVecino = null;
					for(Long vecino : vecinos) {
						if(vecino.equals(dogId)) {
							elVecino = vecino;
							break;
						}
					}
					if(elVecino!=null) {
						dogItem.setSimilarity((int)( (userSimilarity.userSimilarity(0L, elVecino))*100));
					}
					log.info("---------similaritud-------------");
					log.info(elVecino+"");
					log.info(beantoString(userSimilarity.userSimilarity(0L, elVecino)));
				}
				sendMail(dogForm, user,resultFiltered);
				return resultFiltered;
			}
			//log.info("---------finish-------------");
		} catch (TasteException e) {
			log.info(e.toString());
		}
		return result;
	}
	public static DogContentItem transformFormInDog(DogFormContentItem dogForm){
		DogContentItem user = new DogContentItem();
		
		BeanUtils.copyProperties(dogForm, user);
		user.setId(0L);
		if (dogForm.isIndependent2()){
			user.setIndependent(1);
		}
		//si no impuerta el dinero a gastar, entonces ponemos como si no se hubiera valorado
		if(dogForm.getExpensive()==4){
			user.setExpensive(0);
		}
		user.setSpecialNeeds((dogForm.getSpecialNeeds()+dogForm.getSpecialNeeds2())/2);
		user.setSociable((dogForm.getSociable()+dogForm.getSociable2())/2);
		return user;
	}
	public static GenericDataModel transformDogsInAModel(DogContentItem user,List<DogContentItem> list){
		FastByIDMap<PreferenceArray> preferences =
				 new FastByIDMap<PreferenceArray>();
		//add usuario
		PreferenceArray prefsForUser = new GenericUserPreferenceArray(11);
		prefsForUser.setUserID(0, 0L);
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
		preferences.put(0, prefsForUser);
		//add all dogs
		for (int j=0;j<list.size();j++) {
			PreferenceArray prefsForUser1 = new GenericUserPreferenceArray(11);
			DogContentItem dog= list.get(j);
			int i = j+1;
			prefsForUser1.setUserID(i, dog.getId());
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
			preferences.put(dog.getId(), prefsForUser1);
		}
		return  new GenericDataModel(preferences);
	}
	
	public String beantoString(Object obj) {
		return ToStringBuilder.reflectionToString(obj);
	}
	 
	private void sendMail(DogFormContentItem dogForm, DogContentItem user, List<DogContentItem> resultFiltered){
		String body = "Here are the top 10 dogs recomended for your profile <br>";
		body = body+ "Afinitty - Name of dog <br>";
		for (DogContentItem dogItem : resultFiltered){
			body=body+dogItem.getSimilarity()+ "-" + dogItem.getName() + "<br>";
		}
		sendEmailAPI.readyToSendEmail(dogForm.getEmail(), dogForm.getNombre(),  body);
		body=body+"perfil persona<br>";
		body=body+beantoString(user)+"<br><br>";
		body=body+" respuestas  cuestionario <br>";
		body=body+beantoString(dogForm);
		sendEmailAPI.readyToSendEmail("projectdogadopt@gmail.com", dogForm.getNombre(),  body);
		
	}

}
