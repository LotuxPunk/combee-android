package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import com.example.combee.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GenderSettingsActivity extends AppCompatActivity {

    @BindView(R.id.cb_gender_woman) CheckBox checkBoxWoman;
    @BindView(R.id.cb_gender_man) CheckBox checkBoxMan;
    @BindView(R.id.cb_gender_child) CheckBox checkBoxChild;
    @BindView(R.id.btn_gender_save) Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_settings);

        ButterKnife.bind(this);
        SharedPreferences sharedPref =  getSharedPreferences(getString(R.string.saved_preference_file), Context.MODE_PRIVATE);

        checkBoxWoman.setChecked(sharedPref.getBoolean(getString(R.string.saved_gender_woman), false));
        checkBoxMan.setChecked(sharedPref.getBoolean(getString(R.string.saved_gender_man), false));
        checkBoxChild.setChecked(sharedPref.getBoolean(getString(R.string.saved_gender_child), false));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putBoolean(getString(R.string.saved_gender_woman), checkBoxWoman.isChecked());
                editor.putBoolean(getString(R.string.saved_gender_man), checkBoxMan.isChecked());
                editor.putBoolean(getString(R.string.saved_gender_child), checkBoxChild.isChecked());
                editor.commit();

                finish();
            }
        });
    }


}
