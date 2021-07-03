package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.covid.fragment.CovidActivity;
import com.example.covid.fragment.RsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigasi = findViewById(R.id.bottom_nav);
        navigasi.setOnNavigationItemSelectedListener(this);

        prosesFragment(new CovidActivity());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_covid:
                prosesFragment(new CovidActivity());
                break;
            case R.id.menu_rs:
                prosesFragment(new RsActivity());
                break;

        } return true;
    }

    private boolean prosesFragment(Fragment selectedFragment){
        if (selectedFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main,selectedFragment).commit();
            return true;
        }
        else{
            return false;
        }
    }
}