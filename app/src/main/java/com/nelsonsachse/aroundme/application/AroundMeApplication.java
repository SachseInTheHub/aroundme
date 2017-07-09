package com.nelsonsachse.aroundme.application;

import com.nelsonsachse.aroundme.BuildConfig;

import android.app.Application;

import timber.log.Timber;

public class AroundMeApplication extends Application {

    private final ApplicationComponent applicationComponent = createComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        setupTimber();
    }

    protected ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void setupTimber() {

        // avoid logging unless in the debug build
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
