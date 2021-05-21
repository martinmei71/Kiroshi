package com.martinmei.kiroshihealth.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.martinmei.kiroshihealth.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToMainActivity();
    }

    private void goToMainActivity(){
        new Handler().postDelayed(() -> startActivity(MainActivity.newIntent(SplashActivity.this)), 2000);
        finish();
    }
}