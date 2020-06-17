package com.ofek.rickandmortyexcercise.data.di.managers;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.di.UseCasesProvider;

public interface DataStoresProvider {

    RnMDataStore provideRnMDataStore(RnMService rnMService);
}
