package com.endava.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        CardView journey_onboarding = findViewById(R.id.objective_one);
        journey_onboarding.setOnClickListener(v -> {
            System.out.println("cliiiick");
            Intent intent = new Intent(OnboardingActivity.this, OnboardingJourneys.class);
            intent.putExtra("ONBOARDING_OPTION", "option_1");
            startActivity(intent);
        });
    }
}
