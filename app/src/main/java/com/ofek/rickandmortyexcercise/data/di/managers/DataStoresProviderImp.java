package com.ofek.rickandmortyexcercise.data.di.managers;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;
import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStoreImp;
import com.ofek.rickandmortyexcercise.data.services.RnMService;


public class DataStoresProviderImp implements DataStoresProvider {

    @Override
    public RnMDataStore provideRnMDataStore(RnMService rnMService) {
        return new RnMDataStoreImp(rnMService);
    }
}
