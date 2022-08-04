package com.irhatech.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.mukesh.OtpView;

public class PinActivity extends AppCompatActivity {

    OtpView otp_view;
    ImageButton btn_clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_pin);

        otp_view = findViewById(R.id.otp_view);
        btn_clear = findViewById(R.id.btn_clear);

    }

    public void next(View view) {

        Intent intent = new Intent(PinActivity.this,ProductsActivity.class);
        startActivity(intent);
    }

    public void enterOTP(View view) {

        otp_view.append(getString(R.string.bullet));
    }




    public void clear(View view) {

        otp_view.setText("");

    }
}