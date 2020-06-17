package com.ofek.rickandmortyexcercise.data.datastores;

import com.ofek.rickandmortyexcercise.data.common.exceptions.ResponseException;
import com.ofek.rickandmortyexcercise.data.objects.RnMCharactersCallResponseDto;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@RunWith(JUnit4.class)
public class RnMDataStoreImpTest {

    /**
     * an example of unit test for the DataStore layer
     */
    @Test
    public void verifyEmptyListThrowsException() {
        // test preparation
        RnMService service = Mockito.mock(RnMService.class);
        RnMDataStoreImp dataStoreImp = new RnMDataStoreImp(service);
        int page = 2;
        RnMCharactersCallResponseDto responseDummy = new RnMCharactersCallResponseDto();
        // our test case is to verify null list throws the corresponding exception
        responseDummy.setCharactersList(null);
        // instructs the service to return the dummy response
        Mockito.when(service.loadCharactersList(page)).thenReturn(Single.just(responseDummy));
        // actual test
        Single<PagingResult<List<CharacterObj>>> charactersListStream = dataStoreImp.loadCharactersList(page);
        charactersListStream.test().assertError(ResponseException.class);
    }
}