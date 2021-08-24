package com.example.retromvvm.Fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retromvvm.R;
import com.example.retromvvm.adapter.MovieListAdapter;
import com.example.retromvvm.model.ListModel;
import com.example.retromvvm.viewmodel.MovieListViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<ListModel> movieModelList;
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;
    private MovieListViewModel viewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initilazie(view);
        return view;
    }

    private void initilazie(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        processOfGettingNews();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void processOfGettingNews() {
        viewmodel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewmodel.makeApiCall();
        viewmodel.getMovieList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ListModel>>() {
            @Override
            public void onChanged(ArrayList<ListModel> listModels) {
                if (listModels != null) {
                    movieListAdapter.setMovielist(listModels);
                    movieModelList = listModels;
                    movieListAdapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        movieListAdapter = new MovieListAdapter(getContext(), movieModelList);
        recyclerView.setAdapter(movieListAdapter);
        movieListAdapter.notifyDataSetChanged();
    }
}