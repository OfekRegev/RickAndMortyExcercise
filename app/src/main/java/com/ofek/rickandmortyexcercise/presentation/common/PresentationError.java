package com.ofek.rickandmortyexcercise.presentation.common;

/**
 *
 * see PresentationError implementation for more information about how the error interface works
 */
public interface PresentationError {

    void handle(BasePresentationErrorHandler handlingProtocol);

    interface BasePresentationErrorHandler {

    }
}
