package com.ofek.rickandmortyexcercise.data.di.repositories;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;



public interface RepositoriesProvider {
    CharactersRepo provideCharactersRepo(RnMDataStore rnMDataStore);
}
