package com.ofek.rickandmortyexcercise.data.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RnMCharactersCallResponseDto {

	@SerializedName("info")
	private CallInfoDto info;
	@SerializedName("results")
	private List<RnMCharacterDto> charactersList;
	@SerializedName("error")
	private String errorMessage;

	public List<RnMCharacterDto> getCharactersList() {
		return charactersList;
	}

	public void setCharactersList(List<RnMCharacterDto> charactersList) {
		this.charactersList = charactersList;
	}

	public void setInfo(CallInfoDto info){
		this.info = info;
	}

	public CallInfoDto getInfo(){
		return info;
	}

	@Override
 	public String toString(){
		return 
			"CharactersCallResponseDto{" + 
			"info = '" + info + '\'' + 
			"}";
		}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}