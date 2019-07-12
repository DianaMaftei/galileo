package com.endava.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

//        ImageView arManager = findViewById(R.id.ar_manager);
//        arManager.setOnClickListener( v-> {
//            setContentView(R.layout.ar_layout);
//        });

        CardView journey_onboarding_1 = findViewById(R.id.objective_two);
        journey_onboarding_1.setOnClickListener(v -> {
            System.out.println("cliiiick");
            Intent intent = new Intent(OnboardingActivity.this, OnboardingJourneys.class);
            intent.putExtra("ONBOARDING_OPTION", "option_2");
            startActivity(intent);
        });

        CardView journey_onboarding_2 = findViewById(R.id.objective_three);
        journey_onboarding_2.setOnClickListener(v -> {
            System.out.println("cliiiick");
            Intent intent = new Intent(OnboardingActivity.this, OnboardingJourneys.class);
            intent.putExtra("ONBOARDING_OPTION", "option_3");
            startActivity(intent);
        });

        CardView journey_onboarding_3 = findViewById(R.id.objective_four);
        journey_onboarding_3.setOnClickListener(v -> {
            System.out.println("cliiiick");
            Intent intent = new Intent(OnboardingActivity.this, OnboardingJourneys.class);
            intent.putExtra("ONBOARDING_OPTION", "option_4");
            startActivity(intent);
        });

        CardView journey_onboarding_4 = findViewById(R.id.objective_five);
        journey_onboarding_4.setOnClickListener(v -> {
            System.out.println("cliiiick");
            Intent intent = new Intent(OnboardingActivity.this, OnboardingJourneys.class);
            intent.putExtra("ONBOARDING_OPTION", "option_5");
            startActivity(intent);
        });
    }
}
