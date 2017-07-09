package com.nelsonsachse.aroundme.api;

import com.nelsonsachse.aroundme.BuildConfig;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Utils API class to add base query parameters to the every request
 */
class ParameterInterceptor implements Interceptor {

    @Override public Response intercept(@NonNull final Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder builder = originalHttpUrl.newBuilder()
                .addQueryParameter("oauth_token", BuildConfig.OAUTH_TOKEN)
                .addQueryParameter("v", BuildConfig.VERSION_DATE);

        Request.Builder requestBuilder = original.newBuilder()
                .url(builder.build())
                .method(original.method(), original.body());

        Request request = requestBuilder
                .addHeader("Accept", "application/json")
                .build();

        return chain.proceed(request);
    }
}
