package com.nelsonsachse.aroundme.api.explore;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nelsonsachse.aroundme.BuildConfig;
import com.nelsonsachse.aroundme.application.RxModule;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Singleton
public class ExploreService {

    private final Scheduler ioScheduler;
    private ExploreService.Api api;

    @Inject ExploreService(final OkHttpClient client,
            final @Named(RxModule.IO) Scheduler ioScheduler) {
        this.ioScheduler = ioScheduler;

        api = new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }

    public Single<ExploreResponse> getExploreVenues(final String placeName, final String location) {
        return api.exploreVenues(placeName, location).subscribeOn(ioScheduler);
    }

    public interface Api {

        @GET("venues/explore")
        Single<ExploreResponse> exploreVenues(@Query("query") String placeName, @Query("near") String location);
    }
}
