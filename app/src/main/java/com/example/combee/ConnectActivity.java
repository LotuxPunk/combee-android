package com.example.combee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectActivity extends AppCompatActivity {

    @BindView(R.id.connect_email_input)
    EditText emailInput;

    @BindView(R.id.connect_password_input)
    EditText passwordInput;

    @BindView(R.id.connect_submit_btn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ButterKnife.bind(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isInputEmpty(emailInput.getText().toString()) || Util.isInputEmpty(passwordInput.getText().toString())) {
                    Toast.makeText(v.getContext(), getResources().getString(R.string.please_fill_all), Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("my-app", "On peut fetch");
                }
            }
        });
    }
}
