package com.nelsonsachse.aroundme.feature;

import com.nelsonsachse.aroundme.api.data.Groups;
import com.nelsonsachse.aroundme.api.data.Items;
import com.nelsonsachse.aroundme.api.data.Venue;
import com.nelsonsachse.aroundme.api.explore.ExploreService;
import com.nelsonsachse.aroundme.application.RxModule;
import com.nelsonsachse.aroundme.base.BasePresenter;
import com.nelsonsachse.aroundme.base.PerViewScope;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

@PerViewScope
public class HomePresenter extends BasePresenter<HomePresenter.View> {

    /** for the purpose of testing the API the location is static. Later to be replaced with user current location */
    private static final String SEARCH_LOCATION = "london";

    private final Scheduler uiScheduler;
    private final ExploreService service;
    private PublishSubject<String> searchSubject = PublishSubject.create();

    @Inject
    public HomePresenter(final ExploreService service,
            @Named(RxModule.UI) final Scheduler uiScheduler) {
        this.uiScheduler = uiScheduler;
        this.service = service;
    }

    @Override public void onViewAttached(@NonNull final View view) {
        super.onViewAttached(view);

        disposeOnViewDetach(onSearchClicked(view));
        disposeOnViewDetach(onSearchExecuted(view));
    }

    private Disposable onSearchClicked(final View view) {
        return view.onSearchClicked()
                .observeOn(uiScheduler)
                .subscribe(search -> searchSubject.onNext(search));
    }

    private Disposable onSearchExecuted(final View view) {
        return searchSubject
                .observeOn(uiScheduler)
                .doOnNext(search -> service.getExploreVenues(search, SEARCH_LOCATION)
                        .observeOn(uiScheduler)
                        .subscribe((exploreResponse, throwable) -> {
                            List<Groups> groups = exploreResponse.getResponse().getGroups();
                            for (Groups group : groups) {
                                List<Items> items = group.getItems();

                                List<Venue> venues = new ArrayList<>();
                                for (Items item : items) {
                                    venues.add(item.getVenue());
                                }
                                view.showVenues(venues);
                            }
                        }))
                .subscribe();
    }

    public interface View {

        void showVenues(List<Venue> venues);

        Observable<String> onSearchClicked();
    }
}
