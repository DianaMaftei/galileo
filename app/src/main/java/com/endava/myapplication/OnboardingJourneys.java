package com.endava.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class OnboardingJourneys extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        switch (intent.getStringExtra("ONBOARDING_OPTION")) {
            case "option_1":
                setContentView(R.layout.journey_objective_1);
                break;
            case "option_2":
                setContentView(R.layout.journey_objective_2);
                break;
            case "option_3":
                setContentView(R.layout.option_find_your_way_around);
                break;
            case "option_4":
                setContentView(R.layout.journey_objective_3);
                break;
            case "option_5":
                setContentView(R.layout.journey_objective_4);
                break;

        }
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }
    public void navigateToAr(View v) {
        setContentView(R.layout.ar_layout);
    }

}
