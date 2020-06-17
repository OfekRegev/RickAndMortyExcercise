package com.ofek.rickandmortyexcercise.data.datastores;

import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface RnMDataStore {
    Single<PagingResult<List<CharacterObj>>> loadCharactersList(int page);
}
