package com.ofek.rickandmortyexcercise.data.di.repositories;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.data.repositories.RnMRepoImp;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;


public class RepositoriesProviderImp implements RepositoriesProvider {


    @Override
    public CharactersRepo provideCharactersRepo(RnMDataStore rnMDataStore) {
        return new RnMRepoImp(rnMDataStore);
    }
}
