package com.example.retromvvm.Fragment;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.retromvvm.R;
import com.example.retromvvm.adapter.MovieListAdapter;
import com.example.retromvvm.model.ListModel;
import com.example.retromvvm.viewmodel.MovieListViewModel;

import java.util.ArrayList;
public class SearchedNews extends Fragment {
    private ArrayList<ListModel> gettingnews;
    private MovieListAdapter movieListAdapter;
    private String type;
    private RecyclerView recyclerView;
    private int imagecode;
    private ImageView imageView, arrowback;
    private TextView searchname;
    public SearchedNews(String type, int imagecode) {
        this.type = type;
        this.imagecode = imagecode;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searched_news, container, false);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.searchednewsrecyclerview);
        initialize(view);
        getSearchedNews();

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new SearchFragment()).commit();
            }
        });
    }
    private void initialize(View view) {
        arrowback=view.findViewById(R.id.arrowback);
        imageView=view.findViewById(R.id.searchedimage);
        searchname=view.findViewById(R.id.searhcedname);
        imageView.setBackgroundResource(imagecode);
        searchname.setText(type);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getSearchedNews() {
        Bundle bundle = getArguments();
        MovieListViewModel viewmodel1;
        viewmodel1 = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewmodel1.setQ(type);
        viewmodel1.makeApiCall();
        viewmodel1.getMovieList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ListModel>>() {
            @Override
            public void onChanged(ArrayList<ListModel> arrayList) {
                gettingnews = arrayList;
                movieListAdapter.setMovielist(arrayList);
                movieListAdapter.notifyDataSetChanged();
            }
        });
        movieListAdapter = new MovieListAdapter(getContext(), gettingnews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(movieListAdapter);

        movieListAdapter.notifyDataSetChanged();
        recyclerView.setNestedScrollingEnabled(true);
    }
}