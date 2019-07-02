package com.endava.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainOptionsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_find_your_way_around);

        ImageView findAColleagueImgView = findViewById(R.id.findColleague);
        findAColleagueImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cliiiick");
                Intent picture_intent = new Intent(MainOptionsActivity.this, SearchActivity.class);
                startActivity(picture_intent );
            }
        });
    }
}
