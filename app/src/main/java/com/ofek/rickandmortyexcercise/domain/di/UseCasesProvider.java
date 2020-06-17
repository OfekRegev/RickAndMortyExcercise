package com.ofek.rickandmortyexcercise.domain.di;

import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;
import com.ofek.rickandmortyexcercise.domain.usecases.GetCharactersList;

import java.util.List;

import io.reactivex.rxjava3.core.SingleTransformer;

public interface UseCasesProvider {
    GetCharactersList provideGetCharactersList(CharactersRepo charactersRepo, SingleTransformer<PagingResult<List<CharacterObj>>, PagingResult<List<CharacterObj>>> transformer);
}
