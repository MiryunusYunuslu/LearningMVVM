package com.example.retromvvm.model;

import com.google.gson.annotations.SerializedName;

public class ListModel {
    @SerializedName("source")
    private Source source;
    @SerializedName("name")
    private String name;
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("urlToImage")
    private String urlToIMage;
    @SerializedName("publishedAt")
    private String publishedAt;
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToIMage() {
        return urlToIMage;
    }
}
