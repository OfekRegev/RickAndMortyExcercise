package com.ofek.rickandmortyexcercise.domain.common;


import io.reactivex.rxjava3.core.SingleTransformer;

/**
 * provides transformers which performs operation on the current thread
 * usually used for unit tests because unit tests most of the time should avoid running on background threads
 */
public class SynchronousTransformers {

    public static  <T> SingleTransformer<T,T> getSingleTransformer(){
        return upstream -> upstream;
    }
}
