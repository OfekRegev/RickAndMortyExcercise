package com.ofek.rickandmortyexcercise.presentation.mappers;

import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.presentation.objects.UiCharacter;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class UiMappers {
    public static UiCharacter mapCharacterObjToUiCharacter(CharacterObj characterObj) {
        UiCharacter uiCharacter = new UiCharacter();
        uiCharacter.setGender(characterObj.getGender());
        uiCharacter.setImageUrl(characterObj.getImageUrl());
        uiCharacter.setName(characterObj.getName());
        uiCharacter.setSpecies(characterObj.getSpecies());
        return uiCharacter;
    }

    public static PagingResult<List<UiCharacter>> mapPagingResultToUi(PagingResult<List<CharacterObj>> listPagingResult) {
        // copying the result to a new one which holds the UiCharacter type
        PagingResult<List<UiCharacter>> newResult = new PagingResult<>();
        newResult.setMaxPage(listPagingResult.getMaxPage());
        newResult.setPage(listPagingResult.getPage());
        // iterates the list and mapping it to UiCharacter
        List<UiCharacter> characterList = Observable.fromIterable(listPagingResult.getResult())
                .map(UiMappers::mapCharacterObjToUiCharacter).toList().blockingGet();
        newResult.setResult(characterList);
        return newResult;
    }
}
