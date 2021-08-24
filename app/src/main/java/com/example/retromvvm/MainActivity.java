package com.example.retromvvm;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.retromvvm.Fragment.DialogExample;
import com.example.retromvvm.Fragment.HomeFragment;
import com.example.retromvvm.Fragment.ProfileFragment;
import com.example.retromvvm.Fragment.SearchFragment;
import com.example.retromvvm.adapter.MovieListAdapter;
import com.example.retromvvm.model.ListModel;
import com.example.retromvvm.model.MovieModel;
import com.example.retromvvm.viewmodel.MovieListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity  {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ProfileFragment profileFragment;
    public  FragmentManager fragmentManager=getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setfragment(homeFragment);
        navigationBarFragmentChooser();
    }
    public  void setfragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment).commit();
    }
    private void navigationBarFragmentChooser() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Home:
                        setfragment(homeFragment);
                        return true;
                    case R.id.Search:
                        setfragment(searchFragment);
                        return true;
                    case R.id.Profile:
                        setfragment(profileFragment);
                        return true;
                    default:return false;
                }
            }
        });
    }
    private void initialize() {
        bottomNavigationView = findViewById(R.id.navigation_bar);
        frameLayout = findViewById(R.id.framelayout);
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
    }


}