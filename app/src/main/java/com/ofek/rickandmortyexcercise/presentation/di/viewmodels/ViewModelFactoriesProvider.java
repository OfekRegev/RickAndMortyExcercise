package com.ofek.rickandmortyexcercise.presentation.di.viewmodels;

import com.ofek.rickandmortyexcercise.domain.usecases.GetCharactersList;
import com.ofek.rickandmortyexcercise.presentation.characters_list.CharactersListScreenVM;

import io.reactivex.rxjava3.core.Scheduler;

public interface ViewModelFactoriesProvider {

    CharactersListScreenVM.Factory provideCharactersListVMFactory(GetCharactersList getCharactersList, Scheduler scheduler);
}
