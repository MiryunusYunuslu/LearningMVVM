package com.example.retromvvm.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retromvvm.R;
import com.example.retromvvm.adapter.SearchingAdapter;
public class SearchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendDataToAdapter(view);

    }
    private void sendDataToAdapter(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.searchrecylerview);
        SearchingAdapter searchingAdapter;
        int [] images={R.drawable.corona,R.drawable.crypto,R.drawable.game,R.drawable.world,R.drawable.breaking,R.drawable.global,R.drawable.entertainment,R.drawable.technology};
        String [] searchnames={"Corona","Crypto","Game","World","Breaking","Global","Entertainment","Technology"};
        searchingAdapter=new SearchingAdapter(getContext(),images,searchnames);
        LinearLayoutManager linearLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchingAdapter);
        searchingAdapter.notifyDataSetChanged();
    }
}