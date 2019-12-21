package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.combee.R;
import com.example.combee.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_firstname_input)
    EditText firstnameInput;

    @BindView(R.id.register_lastname_input)
    EditText lastnameInput;

    @BindView(R.id.register_email_input)
    EditText emailInput;

    @BindView(R.id.register_password_input)
    EditText passwordInput;

    @BindView(R.id.register_submit_btn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder errorStr = new StringBuilder();
                Boolean isError = false;

                if(Util.isInputEmpty(firstnameInput.getText().toString()) || Util.isInputEmpty(lastnameInput.getText().toString()) || Util.isInputEmpty(emailInput.getText().toString()) || Util.isInputEmpty(passwordInput.getText().toString())) {
                    errorStr.append(getResources().getString(R.string.please_fill_all));
                    isError = true;
                } else {
                    // Verifier les fameuses conditions

                    if(!Util.isValidEmail(emailInput.getText().toString())) {
                        errorStr.append("\n- " + getResources().getString(R.string.wrong_email));
                        isError = true;
                    }

                    if(!Util.isStrongPassword(passwordInput.getText().toString())) {
                        errorStr.append("\n- " + getResources().getString(R.string.strong_password));
                        isError = true;
                    }
                }


                // Affichage erreur
                if(isError) {
                    Toast.makeText(v.getContext(), errorStr.toString() + "\n", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("my-app", "On peut fetch");
                }
            }
        });
    }
}
