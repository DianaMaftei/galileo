package com.endava.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.endava.myapplication.slider.AppIntroSampleSlider;
import com.github.paolorotolo.appintro.AppIntro;

public class MyIntro extends AppIntro {
    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

//adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro_employee));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro_client));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.app_intro_interviewee));
//        addSlide(AppIntroSampleSlider.newInstance(R.layout.beacon_scan));

// Show and Hide Skip and Done buttons
        showStatusBar(true);
        showSkipButton(false);

// Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        setVibrate(false);
//        setVibrateIntensity(30);

//Add animation to the intro slider
        setDepthAnimation();
    }

    @Override
    public void onSkipPressed() {
        // Do something here when users click or tap on Skip button.
        Toast.makeText(getApplicationContext(),
                getString(R.string.app_intro_skip), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onDonePressed() {
        // Do something here when users click or tap tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
//        // Do something here when slide is changed
//
//        TextView txtView = (TextView) findViewById(R.id.beacon_distance);
//        txtView.setText("backend text");
    }

}
