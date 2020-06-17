package com.ofek.rickandmortyexcercise.data.di.managers;

import com.ofek.rickandmortyexcercise.domain.di.UseCasesModule;

/**
 * see {@link UseCasesModule} for explanation about the module classes
 */

public class DataStoreModule {

    private static DataStoresProvider managersProvider;

    public static void injectProvider(DataStoresProvider provider) {
        managersProvider = provider;
    }

    public static DataStoresProvider getManagersProvider(){
        if (managersProvider == null) {
            injectProvider(new DataStoresProviderImp());
        }
        return managersProvider;
    }
}
