package com.endava.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, MyIntro.class);
        startActivity(i);

        LinearLayout menu_photos = findViewById(R.id.findWayAroundMainOption);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cliiiick");
                Intent picture_intent = new Intent(MainActivity.this, MainOptionsActivity.class);
                startActivity(picture_intent );
            }
        });

    }
}
