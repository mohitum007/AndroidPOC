
package com.mohitum.androidpoc.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO class to present feeds list response
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class FeedResponse {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("rows")
    @Expose
    private List<Feed> feeds = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FeedResponse() {
    }

    /**
     * Feed response constructor with parameters
     *
     * @param title title of feed list
     * @param feeds feeds list
     */
    public FeedResponse(String title, List<Feed> feeds) {
        super();
        this.title = title;
        this.feeds = feeds;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

}
