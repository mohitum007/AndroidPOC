
package com.mohitum.androidpoc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO class to present single feed item
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class Feed {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("imageHref")
    @Expose
    private String imageHref;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Feed() {
    }

    /**
     * Feed constructor with parameters
     *
     * @param title feed title
     * @param description feed description
     * @param imageHref feed imqage url as string
     */
    public Feed(String title, String description, String imageHref) {
        super();
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

}
