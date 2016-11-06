package com.lauriano.dogadopt.data.contentitem.dog;

import java.io.Serializable;

public class DogFormContentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	



	

	
	//tamaño
	//1)mini
	//2)pequeño
	//3)mediano
	//4)grande
	//5)maxi
	protected int size;
	
	//
	//CARACTERISTICAS FISICAS
	//tipo de pelo
	//1)corto
	//2)no impuerta
	//3)largo
	protected int hair;
	
	//color
	//1)claro
	//2)mediano
	//3)oscuro
	protected int color;
	
	//
	//CARACTERISTICAS DE CARACTER
	//ESCALA DE 1-4 DONDE 1 ES NADA Y 4 ES MUCHO
	//
	protected int active;
	protected int independent;
	protected boolean independent2;
	//si es Si, independent =1 , sino no modifica el valor del independent
	protected int sociable;
	protected int expensive;
	protected int noiseTolerance; //bajo si ladra mucho - alto si no ladra
	protected int specialNeeds;
	
	protected int playful;
	protected int specialNeeds2;
	//specialNeeds sera la media de los dos specialneeds

	protected int affectionate;
	protected int sociable2;
	//sociable seria la media de los dos sociable
	
	
	
	//
	//OTROS FACTORES 
	//
	protected boolean childrens=false;
	protected boolean otherDogs=false;
	protected boolean anyCats=false;
	protected boolean alergies=false;
	
	protected String nombre;
	protected String email;
	
	protected int similarity;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
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
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getIndependent() {
		return independent;
	}
	public void setIndependent(int independent) {
		this.independent = independent;
	}

	public boolean isIndependent2() {
		return independent2;
	}
	public void setIndependent2(boolean independent2) {
		this.independent2 = independent2;
	}
	public int getSociable() {
		return sociable;
	}
	public void setSociable(int sociable) {
		this.sociable = sociable;
	}
	public int getExpensive() {
		return expensive;
	}
	public void setExpensive(int expensive) {
		this.expensive = expensive;
	}
	public int getNoiseTolerance() {
		return noiseTolerance;
	}
	public void setNoiseTolerance(int noiseTolerance) {
		this.noiseTolerance = noiseTolerance;
	}
	public int getSpecialNeeds() {
		return specialNeeds;
	}
	public void setSpecialNeeds(int specialNeeds) {
		this.specialNeeds = specialNeeds;
	}
	public int getPlayful() {
		return playful;
	}
	public void setPlayful(int playful) {
		this.playful = playful;
	}
	public int getSpecialNeeds2() {
		return specialNeeds2;
	}
	public void setSpecialNeeds2(int specialNeeds2) {
		this.specialNeeds2 = specialNeeds2;
	}
	public int getAffectionate() {
		return affectionate;
	}
	public void setAffectionate(int affectionate) {
		this.affectionate = affectionate;
	}
	public int getSociable2() {
		return sociable2;
	}
	public void setSociable2(int sociable2) {
		this.sociable2 = sociable2;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getSimilarity() {
		return similarity;
	}
	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}
	
}
