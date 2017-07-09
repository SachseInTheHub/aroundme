package com.nelsonsachse.aroundme.feature;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.nelsonsachse.aroundme.R;
import com.nelsonsachse.aroundme.api.data.Venue;
import com.nelsonsachse.aroundme.base.BaseActivity;
import com.nelsonsachse.aroundme.base.BasePresenter;
import com.nelsonsachse.aroundme.utils.ViewUtils;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

public class HomeActivity extends BaseActivity<HomePresenter.View> implements HomePresenter.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_edittext_search)
    EditText searchEditView;
    @BindView(R.id.main_button_search)
    Button searchButtonView;

    private final HomeAdapter adapter = new HomeAdapter();

    @Inject HomePresenter presenter;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override protected void onInject() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
        homeComponent.inject(this);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @NonNull @Override protected BasePresenter<HomePresenter.View> getPresenter() {
        return presenter;
    }

    @NonNull @Override protected HomePresenter.View getPresenterView() {
        return this;
    }

    @Override public void showVenues(final List<Venue> venues) {
        adapter.setData(venues);
        recyclerView.setAdapter(adapter);
    }

    @Override public Observable<String> onSearchClicked() {
        return Observable.merge(RxView.clicks(searchButtonView)
                .doOnNext(o -> hideKeyboard()), RxTextView.editorActions(searchEditView)
                .filter(key -> key == EditorInfo.IME_ACTION_DONE)
                .doOnNext(o -> hideKeyboard()))
                .map(o -> searchEditView.getText().toString());
    }

    private void hideKeyboard() {
        ViewUtils.hideKeyboard(this);
    }
}