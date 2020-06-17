package com.ofek.rickandmortyexcercise.domain.repositories;

import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface CharactersRepo {
    Single<PagingResult<List<CharacterObj>>> getCharactersList(int page);
}
