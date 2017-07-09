package com.nelsonsachse.aroundme.application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = { ApplicationModule.class, RxModule.class })
public interface ApplicationComponent {

    @Named(RxModule.UI)
    Scheduler getUiScheduler();

    @Named(RxModule.IO)
    Scheduler getIoScheduler();
}
