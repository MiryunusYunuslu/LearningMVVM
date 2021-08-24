package com.example.retromvvm.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retromvvm.Fragment.HomeFragment;
import com.example.retromvvm.Fragment.SearchFragment;
import com.example.retromvvm.Fragment.SearchedNews;
import com.example.retromvvm.MainActivity;
import com.example.retromvvm.R;
import java.util.zip.Inflater;
public class SearchingAdapter extends RecyclerView.Adapter<SearchingAdapter.RowHolder> {
    private Context context;
    private int[] images;
    private String[] namesofsearching;
    private MainActivity mainActivity;
    public SearchingAdapter(Context context, int[] images, String[] namesofsearching) {
        this.context = context;
        this.images = images;
        this.namesofsearching = namesofsearching;
    }
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.searchrecyclerview, parent, false);
        return new RowHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
       holder.textView.setText(namesofsearching[position]);
       holder.imageView.setBackgroundResource(images[position]);
       holder.imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AppCompatActivity appCompatActivity= (AppCompatActivity) v.getContext();
               SearchedNews searchedNews=new SearchedNews(namesofsearching[position],images[position]);
               appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,searchedNews).addToBackStack(null).commit();
           }
       });
    }
    @Override
    public int getItemCount() {
         return images.length;
    }
    public static class RowHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.searchimage);
            textView = itemView.findViewById(R.id.searchtext);
        }
    }
}
