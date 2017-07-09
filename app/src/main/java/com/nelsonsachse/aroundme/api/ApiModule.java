package com.nelsonsachse.aroundme.api;

import com.nelsonsachse.aroundme.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class ApiModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor loggingInterceptor,
            final ParameterInterceptor parameterInterceptor) {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(parameterInterceptor);
        return clientBuilder.build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return loggingInterceptor;
    }

    @Provides
    @Singleton
    ParameterInterceptor provideParameterInterceptor() {
        return new ParameterInterceptor();
    }
}
