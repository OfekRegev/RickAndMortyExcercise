package com.ofek.rickandmortyexcercise.data.di.repositories;

import com.ofek.rickandmortyexcercise.domain.di.UseCasesModule;

/**
 * see {@link UseCasesModule} for explanation about the module classes
 */
public class RepositoriesModule {
    
    private static RepositoriesProvider repositoriesProvider;
    
    public static void injectProvider(RepositoriesProvider provider) {
        repositoriesProvider = provider;
    }
    
    public static RepositoriesProvider getRepositoriesProvider(){
        if (repositoriesProvider == null) {
            injectProvider(new RepositoriesProviderImp());
        }
        return repositoriesProvider;
    }
}
