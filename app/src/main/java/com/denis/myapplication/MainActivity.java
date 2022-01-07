package com.denis.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        savedInstanceState.containsKey("param1");
//        int num = savedInstanceState.getInt("param1");
        if(savedInstanceState == null){
//           showFragment(FirstFragment.class);

        }
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }


    public void showFirstFragment(){ showFragment(HomeFragment.class);}
    public void showThirdFragment(){ showFragment(SearchFragment.class);}
    public void showSecondFragment(){ showFragment(ContactsFragment.class);}

    public void goToSecondPage(View view){
        Intent intent = new Intent("com.denis.myapplication.secondActivity");
        startActivity(intent);

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        switch (item.getItemId()){
            case R.id.Nav_Home:
                showFirstFragment();
                break;
            case R.id.Nav_Fav:
                showSecondFragment();
                break;
            case R.id.Nav_Search:
                showThirdFragment();
                break;
            case R.id.Nav_To_Second_Page:
                goToSecondPage(item.getActionView());
        }
        return true;
    };

    private void showFragment(@NonNull Class<? extends Fragment> fragmentClass) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, fragmentClass, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("param1", 10);
    }
}