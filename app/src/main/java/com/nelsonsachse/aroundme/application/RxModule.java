package com.nelsonsachse.aroundme.application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
@Module
public class RxModule {

    public static final String UI = "ui";
    public static final String IO = "io";

    @Provides
    @Named(UI)
    Scheduler provideUIScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(IO)
    Scheduler provideIoScheduler() {
        return Schedulers.io();
    }
}
