package com.ofek.rickandmortyexcercise.data.di.managers;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.di.UseCasesProvider;

/**
 * see {@link UseCasesProvider} for explanation about the provider classes
 */
public interface DataStoresProvider {

    RnMDataStore provideRnMDataStore(RnMService rnMService);
}
