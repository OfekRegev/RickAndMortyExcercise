package com.ofek.rickandmortyexcercise.presentation.di.viewmodels;

import com.ofek.rickandmortyexcercise.domain.usecases.GetCharactersList;
import com.ofek.rickandmortyexcercise.presentation.characters_list.CharactersListScreenVM;

import io.reactivex.rxjava3.core.Scheduler;

/**
 * see {@link com.ofek.rickandmortyexcercise.domain.di.UseCasesProvider} for explanation about the provider classes
 */
public class ViewModelFactoriesProviderImp implements ViewModelFactoriesProvider {

    @Override
    public CharactersListScreenVM.Factory provideCharactersListVMFactory(GetCharactersList getCharactersList, Scheduler scheduler) {
        return new CharactersListScreenVM.Factory(getCharactersList,scheduler);
    }
}
