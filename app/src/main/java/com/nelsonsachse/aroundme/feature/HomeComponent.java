package com.nelsonsachse.aroundme.feature;

import com.nelsonsachse.aroundme.application.ApplicationComponent;
import com.nelsonsachse.aroundme.base.PerViewScope;

import dagger.Component;

@PerViewScope
@Component(dependencies = ApplicationComponent.class)
public interface HomeComponent {

    void inject(HomeActivity activity);
}
