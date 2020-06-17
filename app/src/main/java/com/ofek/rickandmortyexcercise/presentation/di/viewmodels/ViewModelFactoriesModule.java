package com.ofek.rickandmortyexcercise.presentation.di.viewmodels;

/**
 * see {@link com.ofek.rickandmortyexcercise.domain.di.UseCasesModule} for explanation about the module classes
 */
public class ViewModelFactoriesModule {

    private static ViewModelFactoriesProvider managersProvider;

    public static void injectProvider(ViewModelFactoriesProvider provider) {
        managersProvider = provider;
    }

    public static ViewModelFactoriesProvider getFactoriesProvider(){
        if (managersProvider == null) {
            injectProvider(new ViewModelFactoriesProviderImp());
        }
        return managersProvider;
    }
}
