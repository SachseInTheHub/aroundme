package com.nelsonsachse.aroundme.feature;

import com.nelsonsachse.aroundme.R;
import com.nelsonsachse.aroundme.api.data.Venue;
import com.nelsonsachse.aroundme.base.BaseActivity;
import com.nelsonsachse.aroundme.base.BasePresenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

public class HomeActivity extends BaseActivity<HomePresenter.View> implements HomePresenter.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject HomePresenter presenter;

    @Override protected void onInitialize() {
        super.onInitialize();
    }

    @Override protected void onInject() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        homeComponent.inject(this);
    }

    @Override protected int getLayoutId() {
        return R.layout.home_activity;
    }

    @NonNull @Override protected BasePresenter<HomePresenter.View> getPresenter() {
        return presenter;
    }

    @NonNull @Override protected HomePresenter.View getPresenterView() {
        return this;
    }

    @Override public void showVenues(final List<Venue> venues) {

    }

    @Override public Observable<Object> onSearchClicked() {
        return null;
    }
}