package com.nelsonsachse.aroundme.application;

import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module class ApplicationModule {

    private final Application application;

    ApplicationModule(@NonNull final Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }
}
