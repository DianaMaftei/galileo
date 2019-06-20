package com.endava.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, MyIntro.class);
        i.putExtra("role", getIntent().getStringExtra("role"));
        startActivity(i);

    }

    public void launchBeacons(View view) {
        Intent i = new Intent(MainActivity.this, Beacons.class);
        startActivity(i);
    }
}
