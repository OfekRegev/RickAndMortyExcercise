package com.ofek.rickandmortyexcercise.data.di.common;

import com.ofek.rickandmortyexcercise.domain.di.UseCasesModule;

/**
 * see {@link UseCasesModule} for explanation about the module classes
 */
public class DataCommonModule {

    private static DataCommonProvider managersProvider;

    public static void injectProvider(DataCommonProvider provider) {
        managersProvider = provider;
    }

    public static DataCommonProvider getManagersProvider(){
        if (managersProvider == null) {
            injectProvider(new DataCommonProviderImp());
        }
        return managersProvider;
    }
}
