package com.ofek.rickandmortyexcercise.data.repositories;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

/**
 * this repository is created for loading Rick and Morty data
 *
 * note: This repository might seems to be useless and unnecessary because this application doesn't have a complicated business logic.
 * But, usually on real projects where the business logic is much more complicated this layer is required for good separation of concerns.
 * On real projects this layer will contain the application business logic such as batching calls, save data on local db, data validation, etc.
 */
public class RnMRepoImp implements CharactersRepo {

    private final RnMDataStore dataStore;

    public RnMRepoImp(RnMDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Single<PagingResult<List<CharacterObj>>> getCharactersList(int page) {
        return dataStore.loadCharactersList(page);
    }



}
