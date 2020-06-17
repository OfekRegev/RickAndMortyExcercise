package com.ofek.rickandmortyexcercise.domain.usecases;

import com.ofek.rickandmortyexcercise.domain.common.BaseSingleUseCase;
import com.ofek.rickandmortyexcercise.domain.objects.CharacterObj;
import com.ofek.rickandmortyexcercise.domain.objects.PagingResult;
import com.ofek.rickandmortyexcercise.domain.repositories.CharactersRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;

/**
 * this use case represents the intention of loading a characters list
 */
public class GetCharactersList extends BaseSingleUseCase<PagingResult<List<CharacterObj>>> {

    private static final String PAGE_KEY = "page";
    private final CharactersRepo charactersRepo;
    protected GetCharactersList(SingleTransformer<PagingResult<List<CharacterObj>>, PagingResult<List<CharacterObj>>> transformer, CharactersRepo charactersRepo) {
        super(transformer);
        this.charactersRepo = charactersRepo;
    }

    @Override
    protected Single<PagingResult<List<CharacterObj>>> createSourceSingle(Map<String, Object> params) {
        int page = (int) params.get(PAGE_KEY);
        return charactersRepo.getCharactersList(page);
    }

    /**
     * preparing the stream for subscription
     * @param page the required page to load
     * @return the loading characters stream with the parameters applied
     */
    public Single<PagingResult<List<CharacterObj>>> getCharacters(int page) {
        Map<String, Object> params = new HashMap<>(1);
        params.put(PAGE_KEY,page);
        return createStream(params);
    }

}
