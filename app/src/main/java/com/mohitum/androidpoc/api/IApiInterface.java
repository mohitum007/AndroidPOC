package com.mohitum.androidpoc.api;

import com.mohitum.androidpoc.model.FeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit interface for calling API methods
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public interface IApiInterface {

    @GET(ApiClient.AppUrls.GET_FEEDS_API)
    Call<FeedResponse> fetchFeeds();
}
