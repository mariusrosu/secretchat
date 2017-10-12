package com.example.marosu.secretchat.model.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.marosu.secretchat.auth.register.RegisterPresenter.AUTHORIZATION;

/**
 * Created by Marius-Andrei Rosu on 8/9/2017.
 */
public final class SecretChatClient {
    private static final String SECRET_CHAT_URL = "https://secrechat.pagekite.me/api/";
    private static Retrofit retrofit;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SECRET_CHAT_URL)
                    .client(createHttpInterceptorClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static SecretChatApi createApi() {
        return getClient().create(SecretChatApi.class);
    }

    private static OkHttpClient createHttpInterceptorClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(chain -> {
            final Request request = chain.request().newBuilder()
                    .addHeader(AUTHORIZATION, "value")
                    .build();
            return chain.proceed(request);
        });

        return httpClient.build();
    }
}
