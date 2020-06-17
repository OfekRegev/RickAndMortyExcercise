package com.ofek.rickandmortyexcercise.domain.common;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * this transformer applies an operator to provide the stream a thread pool to execute on
 */
public class AsyncTransformers {

    /**
     * provides transformer for Single stream which applies a thread pool for async operations
     * the callback will be called on the main thread
     */
    public static <T> SingleTransformer<T, T> getSingleTransformer() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
