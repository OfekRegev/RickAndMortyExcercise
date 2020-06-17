package com.ofek.rickandmortyexcercise.data.di.common;

import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.di.UseCasesProvider;

/**
 * see {@link UseCasesProvider} for explanation about the provider classes
 */
public interface DataCommonProvider {

    RnMService provideRnMService();
}
