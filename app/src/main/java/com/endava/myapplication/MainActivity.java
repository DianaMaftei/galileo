package com.endava.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, MyIntro.class);
//        i.putExtra("role", getIntent().getStringExtra("role"));
        i.putExtra("role", "employee");
        startActivity(i);

        ImageView findYourWayAround = findViewById(R.id.findWayAroundMainOption);
        findYourWayAround.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainOptionsActivity.class);
            startActivity(intent);
        });

        ImageView onboarding = findViewById(R.id.newJoinerMainOption);
        onboarding.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
            startActivity(intent);
        });

        ImageView calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

        ImageView endavaInfo = findViewById(R.id.info_button);
        endavaInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EndavaInfoActivity.class);
            startActivity(intent);
        });



    }

    public void launchBeacons(View view) {
        Intent i = new Intent(MainActivity.this, BeaconsActivity.class);
        startActivity(i);
    }

}
