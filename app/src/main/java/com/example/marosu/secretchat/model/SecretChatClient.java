package com.example.marosu.secretchat.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Marius-Andrei Rosu on 8/9/2017.
 */
public class SecretChatClient {
    private static final String SECRET_CHAT_URL = "https://secrechat.pagekite.me";
    private static Retrofit retrofit;

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SECRET_CHAT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static SecretChatApi createApi() {
        return getClient().create(SecretChatApi.class);
    }
}
