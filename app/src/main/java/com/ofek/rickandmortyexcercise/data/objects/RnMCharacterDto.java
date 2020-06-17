package com.ofek.rickandmortyexcercise.data.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RnMCharacterDto {

	@SerializedName("image")
	private String image;

	@SerializedName("gender")
	private String gender;

	@SerializedName("species")
	private String species;

	@SerializedName("created")
	private String created;

	@SerializedName("origin")
	private LocationDto origin;

	@SerializedName("name")
	private String name;

	@SerializedName("location")
	private LocationDto location;

	@SerializedName("episode")
	private List<String> episode;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("url")
	private String url;

	@SerializedName("status")
	private String status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSpecies(String species){
		this.species = species;
	}

	public String getSpecies(){
		return species;
	}

	public void setCreated(String created){
		this.created = created;
	}

	public String getCreated(){
		return created;
	}

	public void setOrigin(LocationDto origin){
		this.origin = origin;
	}

	public LocationDto getOrigin(){
		return origin;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLocation(LocationDto location){
		this.location = location;
	}

	public LocationDto getLocation(){
		return location;
	}

	public void setEpisode(List<String> episode){
		this.episode = episode;
	}

	public List<String> getEpisode(){
		return episode;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RnMCharacter{" + 
			"image = '" + image + '\'' + 
			",gender = '" + gender + '\'' + 
			",species = '" + species + '\'' + 
			",created = '" + created + '\'' + 
			",origin = '" + origin + '\'' + 
			",name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",episode = '" + episode + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",url = '" + url + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}