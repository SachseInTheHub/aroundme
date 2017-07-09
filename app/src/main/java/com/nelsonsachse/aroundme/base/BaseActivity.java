package com.nelsonsachse.aroundme.base;

import com.nelsonsachse.aroundme.application.ApplicationComponent;
import com.nelsonsachse.aroundme.application.AroundMeApplication;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<T> extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onInject();
        onInitialize();
        getPresenter().onViewAttached(getPresenterView());
    }

    @Override
    protected void onResume() {
        getPresenter().onViewWillShow(getPresenterView());
        super.onResume();
    }

    @Override
    protected void onPause() {
        getPresenter().onViewWillHide();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        getPresenter().onViewDetached();
        super.onDestroy();
    }

    /**
     * Perform remaining Activity initialization. {@link BasePresenter#onViewAttached(T)}
     * will be called next.
     */
    protected void onInitialize() {
    }

    public ApplicationComponent getApplicationComponent() {
        return ((AroundMeApplication) getApplication()).getApplicationComponent();
    }

    protected abstract void onInject();

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract BasePresenter<T> getPresenter();

    @NonNull
    protected abstract T getPresenterView();
}
