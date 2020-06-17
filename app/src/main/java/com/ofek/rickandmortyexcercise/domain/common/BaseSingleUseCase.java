package com.ofek.rickandmortyexcercise.domain.common;

import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;


public abstract class BaseSingleUseCase<T> {

    private final SingleTransformer<T,T> transformer;

    protected BaseSingleUseCase(SingleTransformer<T, T> transformer) {
        this.transformer = transformer;
    }

    /**
     * applies transformer to the source stream
     * @param params the use case parameters
     * @return the transformed stream
     */
    protected Single<T> createStream(Map<String,Object> params) {
        return createSourceSingle(params).compose(transformer);
    }

    /**
     * the method which provides the source stream
     * @param params the use case parameters
     * @return the source stream
     */
    protected abstract Single<T> createSourceSingle(Map<String, Object> params);
}
