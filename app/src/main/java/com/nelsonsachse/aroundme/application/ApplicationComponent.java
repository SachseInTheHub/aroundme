package com.nelsonsachse.aroundme.application;

import com.nelsonsachse.aroundme.api.ApiModule;
import com.nelsonsachse.aroundme.api.explore.ExploreService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

@Singleton
@Component(modules = { ApplicationModule.class, ApiModule.class, RxModule.class })
public interface ApplicationComponent {

    ExploreService getExploreService();

    @Named(RxModule.UI)
    Scheduler getUiScheduler();

    @Named(RxModule.IO)
    Scheduler getIoScheduler();
}
