package com.example.retromvvm.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetroInstance {
    public static String BASE_URL="https://newsapi.org/v2/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            Gson gson=new GsonBuilder().setLenient().create();
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
