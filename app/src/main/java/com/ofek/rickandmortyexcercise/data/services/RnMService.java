package com.ofek.rickandmortyexcercise.data.services;

import com.ofek.rickandmortyexcercise.data.objects.RnMCharactersCallResponseDto;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RnMService {
    @GET("character/?page={pagedId}")
    Single<RnMCharactersCallResponseDto> loadCharactersList(@Path("pageId") int page);
}
