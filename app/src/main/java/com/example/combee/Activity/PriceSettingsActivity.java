package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.combee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceSettingsActivity extends AppCompatActivity {

    @BindView(R.id.cb_price_one)
    CheckBox checkBoxPriceOne;

    @BindView(R.id.cb_price_two)
    CheckBox checkBoxPriceTwo;

    @BindView(R.id.cb_price_three)
    CheckBox checkBoxPriceThree;

    @BindView(R.id.btn_price_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_settings);

        ButterKnife.bind(this);
        SharedPreferences sharedPref =  getSharedPreferences(getString(R.string.saved_preference_file), Context.MODE_PRIVATE);

        checkBoxPriceOne.setChecked(sharedPref.getBoolean(getString(R.string.saved_price_one), false));
        checkBoxPriceTwo.setChecked(sharedPref.getBoolean(getString(R.string.saved_price_two), false));
        checkBoxPriceThree.setChecked(sharedPref.getBoolean(getString(R.string.saved_price_three), false));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putBoolean(getString(R.string.saved_price_one), checkBoxPriceOne.isChecked());
                editor.putBoolean(getString(R.string.saved_price_two), checkBoxPriceTwo.isChecked());
                editor.putBoolean(getString(R.string.saved_price_three), checkBoxPriceThree.isChecked());
                editor.commit();

                finish();
            }
        });
    }
}
