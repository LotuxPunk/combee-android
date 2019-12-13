package com.example.combee.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.combee.R;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Integer businessId = getIntent().getIntExtra("BUSINESS_ID", 1);

        Log.i("my-app", businessId.toString());
    }
}
