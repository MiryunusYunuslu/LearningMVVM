package com.example.retromvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModel {
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("articles")
    private ArrayList<ListModel> articles;
    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public ArrayList<ListModel> getArticles() {
        return articles;
    }


}
