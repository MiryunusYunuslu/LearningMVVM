package com.example.retromvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.example.retromvvm.Fragment.WebView;
import com.example.retromvvm.MainActivity;
import com.example.retromvvm.R;
import com.example.retromvvm.model.ListModel;
import com.example.retromvvm.model.MovieModel;

import java.util.ArrayList;
import java.util.List;
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.RowHoler> {
    private Context context;
    private ArrayList<ListModel> arrayList;

    public MovieListAdapter(Context context, ArrayList<ListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }
        public void setMovielist(ArrayList<ListModel> arrayList) {
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }
    @NonNull
    @Override
    public RowHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowholder, parent, false);
        return new RowHoler(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RowHoler holder, int position) {
        holder.titleOfNews.setText(arrayList.get(position).getTitle());
        holder.publishedTime.setText(arrayList.get(position).getPublishedAt().substring(0,10));
        if(arrayList.get(position).getUrlToIMage()!=null){
            Glide.with(context).load(arrayList.get(position).getUrlToIMage()).into(holder.imageofnews);
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
                WebView webView=new WebView();
                webView.setUrl(arrayList.get(position).getUrl());
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,webView).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public int getItemCount() {
        if (this.arrayList != null) {
            return this.arrayList.size();
        }
        return 0;
    }

    public static class RowHoler extends RecyclerView.ViewHolder {
        TextView titleOfNews, publishedTime;
        ImageView imageofnews;
        ConstraintLayout constraintLayout;


        public RowHoler(@NonNull View itemView) {
            super(itemView);
            titleOfNews = itemView.findViewById(R.id.titleofnews);
            publishedTime = itemView.findViewById(R.id.publishedtime);
            imageofnews = itemView.findViewById(R.id.imagenews);
            constraintLayout= itemView.findViewById(R.id.constrainLayout);
        }
    }
}
