package com.ofek.rickandmortyexcercise.data.datastores;

import com.ofek.rickandmortyexcercise.data.common.exceptions.ResponseException;
import com.ofek.rickandmortyexcercise.data.mappers.RnMMappers;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * this data store is responsible for api calls to https://rickandmortyapi.com/
 * note:
 * the data store layer is responsible to load data from api and map the dto objects to data objects required for the repository layer.
 */
public class RnMDataStoreImp implements RnMDataStore {

    private final RnMService service;

    public RnMDataStoreImp(RnMService service) {
        this.service = service;
    }

    @Override
    public Single<PagingResult<List<CharacterObj>>> loadCharactersList(int page) {
        return service.loadCharactersList(page)
                // when the api call completes it's required to check whether the response is valid and usable or not
                // if the result contains an error or missing fields an exception should be thrown
                .map(charactersCallResponseDto -> {
                    if (charactersCallResponseDto.getErrorMessage() != null
                            || charactersCallResponseDto.getCharactersList() == null
                            || charactersCallResponseDto.getInfo() == null) {
                        throw new ResponseException(charactersCallResponseDto.getErrorMessage());
                    }
                    return charactersCallResponseDto;
                })
                // mapping the api response to the paging result
                .map(charactersCallResponseDtos -> {
                    PagingResult<List<CharacterObj>> result = new PagingResult<>();
                    result.setPage(page);
                    result.setMaxPage(charactersCallResponseDtos.getInfo().getPages());
                    // iterates the list and maps each RnMCharacter to CharacterObject
                    List<CharacterObj> charactersList = Observable.fromIterable(charactersCallResponseDtos.getCharactersList()).map(RnMMappers::mapCharacterDtoToCharacterObj).toList().blockingGet();
                    result.setResult(charactersList);
                    return result;
                });
    }
}
