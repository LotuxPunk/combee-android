package com.example.combee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceSettingsActivity extends AppCompatActivity {

    @BindView(R.id.r_group_price)
    RadioGroup radioGroupPrice;

    @BindView(R.id.btn_price_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_settings);

        ButterKnife.bind(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCheckbox = radioGroupPrice.getCheckedRadioButtonId();

                RadioButton r = (RadioButton) findViewById(selectedCheckbox);

                Log.i("my-app", r.getText().toString());
            }
        });
    }
}
