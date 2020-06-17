package com.ofek.rickandmortyexcercise.data.services;

import com.ofek.rickandmortyexcercise.data.objects.RnMCharactersCallResponseDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RnMService {
    @GET("character/")
    Single<RnMCharactersCallResponseDto> loadCharactersList(@Query("page") int page);
}
