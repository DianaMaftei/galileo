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
        i.putExtra("role", getIntent().getStringExtra("role"));
        startActivity(i);

        ImageView menu_photos = findViewById(R.id.findWayAroundMainOption);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cliiiick");
                Intent picture_intent = new Intent(MainActivity.this, MainOptionsActivity.class);
                startActivity(picture_intent );
            }
        });
    }

    public void launchBeacons(View view) {
        Intent i = new Intent(MainActivity.this, BeaconsActivity.class);
        startActivity(i);
    }

}
