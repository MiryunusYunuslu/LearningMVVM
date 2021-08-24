package com.example.retromvvm.viewmodel;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retromvvm.model.ListModel;
import com.example.retromvvm.model.MovieModel;
import com.example.retromvvm.network.ApiService;
import com.example.retromvvm.network.RetroInstance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@RequiresApi(api = Build.VERSION_CODES.O)
public class MovieListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ListModel>> movieList;
    private String q="all";
    static String currentDate;
    public MovieListViewModel() {
        movieList = new MutableLiveData<>();
    }
    public MutableLiveData<ArrayList<ListModel>> getMovieList(){
        return  movieList;
    }
    public void setQ(String q) {
        this.q = q;
    }
    public void makeApiCall() {
        ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);
        Call<MovieModel> call = apiService.callApi(q, currentDate,"publishedAt","02a50775a2fc445bb056fae4a9950bd2","en");
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    movieList.postValue( response.body().getArticles());
                } else {
                    System.out.println("Not Succesful");
                }
            }
            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    static{
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime currenttime=LocalDateTime.now();
        currentDate=formatter.format(currenttime);
        System.out.println(currentDate);
    }
}
