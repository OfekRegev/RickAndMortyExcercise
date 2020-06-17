package com.ofek.rickandmortyexcercise.data.repositories;

import com.ofek.rickandmortyexcercise.data.datastores.RnMDataStore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class RnMRepoTest {
    /**
     * this is an example of a unit test to the repository layer
     * verifies the dataStore function called only once with the correct parameter
     */
    @Test
    public void validatePageParam(){
        // test preparation
        RnMDataStore dataStore = Mockito.mock(RnMDataStore.class);
        RnMRepoImp rnMRepoImp = new RnMRepoImp(dataStore);
        int page = 3;

        // action
        rnMRepoImp.getCharactersList(page);

        // verify
        Mockito.verify(dataStore,Mockito.times(1)).loadCharactersList(page);
    }
}
