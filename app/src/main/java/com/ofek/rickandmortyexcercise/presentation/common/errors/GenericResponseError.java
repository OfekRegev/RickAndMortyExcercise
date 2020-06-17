package com.ofek.rickandmortyexcercise.presentation.common.errors;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * this error will be handled by a view which implements it's handling protocol.
 * usually on a real project I would create more error types not just generic one,
 * so I could react to all necessary type of errors
 */
public class GenericResponseError implements PresentationError {

    // this boolean helps to verify this error handled only once
    private AtomicBoolean isHandled = new AtomicBoolean(false);

    @Override
    public void handle(BasePresentationErrorHandler handlingProtocol) {
        // the view is passed to this function every time an error is thrown.
        // if the view implements the required protocol the callback on the view will be called and the view will react accordingly
        if (!isHandled.get() && handlingProtocol instanceof GenericResponseErrorProtocol) {
            ((GenericResponseErrorProtocol) handlingProtocol).onGenericResponseError();
            isHandled.set(true);
        }
    }

    interface GenericResponseErrorProtocol extends BasePresentationErrorHandler {
        void onGenericResponseError();
    }
}
