package com.ofek.rickandmortyexcercise.data.di.common;

import com.ofek.rickandmortyexcercise.data.common.Constants;
import com.ofek.rickandmortyexcercise.data.services.RnMService;
import com.ofek.rickandmortyexcercise.domain.di.UseCasesProvider;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataCommonProviderImp implements DataCommonProvider {
    @Override
    public RnMService provideRnMService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(Constants.RICK_AND_MORTY_API_BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build());
        return retrofitBuilder.build().create(RnMService.class);
    }
}
