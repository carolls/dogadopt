package com.lauriano.dogadopt.app;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lauriano.dogadopt.data.entity.dog.Dog;
import com.lauriano.dogadopt.data.repository.dog.DogRepository;

@SpringBootApplication
@ComponentScan("com.lauriano.dogadopt")
@EntityScan("com.lauriano.dogadopt.data.entity")
@EnableJpaRepositories("com.lauriano.dogadopt.data.repository") 

public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
	
    /*
     * INIT DEMO DATA FOR TEST
     */
	@Bean
	public CommandLineRunner demo(DogRepository dogRepository) {
		return (args) -> {
            // save a couple of dogs
            // id, String name, int age, String breedId, 
            //int hair - 1corto 2 largo,
        	//int color 1claro 2mediano 3oscuro,
            //int size 1mini 5 maxi, 
            //int playful, 
            //int sociable, 
            //int independent,
        	//int active, int affectionate, int specialNeeds, int noiseTolerance, int expensive
        	//boolean childrens, boolean otherDogs, boolean anyCats,
        	//boolean alergies
            dogRepository.save(new Dog(1L,"Luke", 3, "bodeguero andalu",Ctes.HAIR.SHORT,Ctes.COLOR.LIGHT, Ctes.SIZE.SMALL,Ctes.CARACTER.ALOT,
            		Ctes.CARACTER.ALOT,Ctes.CARACTER.NOTATALL,Ctes.CARACTER.ALOT,Ctes.CARACTER.ALOT,Ctes.CARACTER.NOTATALL,Ctes.CARACTER.ALOT,Ctes.CARACTER.NOTATALL
            		,Ctes.OTHER.LOVE_CHILDREN,Ctes.OTHER.LOVE_DOGS,Ctes.OTHER.LOVE_CATS,Ctes.OTHER.ALLERGIC));
            dogRepository.save(new Dog(2L,"Amy", 7, "mini-pinscher",Ctes.HAIR.SHORT,Ctes.COLOR.DARK,Ctes.SIZE.MINI,Ctes.CARACTER.LITTLE,
            		1,4,2,2,3,2,Ctes.CARACTER.NOTATALL,false,true,false,false));
            dogRepository.save(new Dog(3L,"Mel", 2, "branco aleman",Ctes.HAIR.SHORT, Ctes.COLOR.DARK,Ctes.SIZE.BIG,Ctes.CARACTER.ALOT,
            		3,3,4,1,2,2,Ctes.CARACTER.MEDIUM,true,true,false,false));
            dogRepository.save(new Dog(4L,"Laika", 3, "labrador",Ctes.HAIR.SHORT,Ctes.COLOR.LIGHT,Ctes.SIZE.BIG,Ctes.CARACTER.ALOT,
            		4,2,4,3,2,2,Ctes.CARACTER.ALOT,false,true,false,false));
            dogRepository.save(new Dog(5L,"Duna", 7, "golden",Ctes.HAIR.LONG,Ctes.COLOR.LIGHT,Ctes.SIZE.BIG,Ctes.CARACTER.ALOT,
            		4,4,3,4,1,4,Ctes.CARACTER.MEDIUM,true,true,true,false));
            
            
            generateDogsAleatory(400,dogRepository);
			// fetch all dogs
			log.info("Dogs found with findAll():");
			log.info("-------------------------------");
			for (Dog dog : dogRepository.findAll()) {
				//log.info(dog.toString());
			}
            log.info("");
		};
	}
	
	public static void generateDogsAleatory(int numDogs,DogRepository dogRepository){
		long param1 = 0;
		for (int i=0; i< numDogs;i++){
			param1=param1++;
			String name = randomIdentifier();
			int age = randInt(0,15);
			String breed= randomIdentifier();
			int hair = randInt(1,3);
			int color = randInt(1,3);
			int size= randInt(1,5);
			int playful=randInt(1,4);
			int sociable=randInt(1,4);
			int independent=randInt(1,4);
			int active=randInt(1,4);
			int affectionate=randInt(1,4);
			int specialNeeds=randInt(1,4);
			int noiseTolerance=randInt(1,4);
			int expensive=randInt(1,4);
			boolean children=getRandomBoolean();
			boolean otherDogs=getRandomBoolean();
			boolean cats=getRandomBoolean();
			boolean alergy=getRandomBoolean();
			
					
			dogRepository.save(new Dog(param1,name, age, breed,hair,color, size,playful,
            		sociable,independent,active,affectionate,specialNeeds,noiseTolerance,expensive
            		,children,otherDogs,cats,alergy));
			
		}
	}




	final static java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	final static Set<String> identifiers = new HashSet<String>();
	public static String randomIdentifier() {
		String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++) {
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        }
	        if(identifiers.contains(builder.toString())) {
	            builder = new StringBuilder();
	        }
	    }
	    return builder.toString();
	}
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	public static boolean getRandomBoolean() {
	    return Math.random() < 0.5;

	     }
	

}
