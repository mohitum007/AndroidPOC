package com.mohitum.androidpoc.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class for retrofit API Client for communicating with web server
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppUrls.API_BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * Constants class for containing app API urls
     * Created by mohitum on 23-11-2017.
     *
     * @version 1.0
     */
    class AppUrls {
        static final String API_BASE_URL = "https://dl.dropboxusercontent.com";
        static final String GET_FEEDS_API = "/s/2iodh4vg0eortkl/facts.json";

    }
}