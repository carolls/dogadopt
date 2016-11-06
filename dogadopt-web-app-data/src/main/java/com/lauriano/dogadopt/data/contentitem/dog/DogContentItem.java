package com.lauriano.dogadopt.data.contentitem.dog;

import java.io.Serializable;

public class DogContentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected Long id;
	
	protected String name;
	
	protected int age;
	
	protected String breed;
	
	//
	//CARACTERISTICAS FISICAS
	//tipo de pelo
	//1)corto
	//2)largo
	protected int hair;
	
	//color
	//1)claro
	//2)mediano
	//3)oscuro
	protected int color;
	
	//tamaño
	//1)mini
	//2)pequeño
	//3)mediano
	//4)grande
	//5)maxi
	protected int size;
	
	//
	//CARACTERISTICAS DE CARACTER
	//ESCALA DE 1-4 DONDE 1 ES NADA Y 4 ES MUCHO
	//
	protected int playful;
	protected int sociable;
	protected int independent;
	protected int active;
	protected int affectionate;
	protected int specialNeeds;
	protected int noiseTolerance; //bajo si ladra mucho - alto si no ladra
	protected int expensive;
	
	//
	//OTROS FACTORES 
	//
	protected boolean childrens;
	protected boolean otherDogs;
	protected boolean anyCats;
	protected boolean alergies;
	//Similaritud
	protected int similarity;
	
	
	public int getHair() {
		return hair;
	}
	public void setHair(int hair) {
		this.hair = hair;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPlayful() {
		return playful;
	}
	public void setPlayful(int playful) {
		this.playful = playful;
	}
	public int getSociable() {
		return sociable;
	}
	public void setSociable(int sociable) {
		this.sociable = sociable;
	}
	public int getIndependent() {
		return independent;
	}
	public void setIndependent(int independent) {
		this.independent = independent;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getAffectionate() {
		return affectionate;
	}
	public void setAffectionate(int affectionate) {
		this.affectionate = affectionate;
	}
	public int getSpecialNeeds() {
		return specialNeeds;
	}
	public void setSpecialNeeds(int specialNeeds) {
		this.specialNeeds = specialNeeds;
	}
	public int getNoiseTolerance() {
		return noiseTolerance;
	}
	public void setNoiseTolerance(int noiseTolerance) {
		this.noiseTolerance = noiseTolerance;
	}
	public int getExpensive() {
		return expensive;
	}
	public void setExpensive(int expensive) {
		this.expensive = expensive;
	}
	public boolean isChildrens() {
		return childrens;
	}
	public void setChildrens(boolean childrens) {
		this.childrens = childrens;
	}
	public boolean isOtherDogs() {
		return otherDogs;
	}
	public void setOtherDogs(boolean otherDogs) {
		this.otherDogs = otherDogs;
	}
	public boolean isAnyCats() {
		return anyCats;
	}
	public void setAnyCats(boolean anyCats) {
		this.anyCats = anyCats;
	}
	public boolean isAlergies() {
		return alergies;
	}
	public void setAlergies(boolean alergies) {
		this.alergies = alergies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public int getSimilarity() {
		return similarity;
	}
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	
}
