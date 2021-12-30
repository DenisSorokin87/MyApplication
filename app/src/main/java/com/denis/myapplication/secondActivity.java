    package com.denis.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.denis.myapplication.Adapters.PageViewAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

    public class secondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new FavoriteFragment());
        fragments.add(new SearchFragment());
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageViewAdapter(this, fragments));
        }

    }