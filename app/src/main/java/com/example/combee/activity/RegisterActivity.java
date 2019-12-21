package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.combee.R;
import com.example.combee.Util;
import com.example.combee.dataAccess.UserDAO;
import com.example.combee.dataAccess.fromDaoModel.UserRegisterForm;
import com.example.combee.model.Token;
import com.example.combee.model.User;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_firstname_input) EditText firstnameInput;
    @BindView(R.id.register_lastname_input) EditText lastnameInput;
    @BindView(R.id.register_email_input) EditText emailInput;
    @BindView(R.id.register_password_input) EditText passwordInput;
    @BindView(R.id.register_submit_btn) Button submitBtn;

    private UserDAO userDAO;
    private String errorToastMessage;

    private class RegisterUser extends AsyncTask<UserRegisterForm, Void, Token> {
        @Override
        protected Token doInBackground(UserRegisterForm... userRegisterForms) {
            Token token = null;

            try {
                token = userDAO.register(userRegisterForms[0]);
            } catch (Exception e) {
                Log.i("my-app", "error detected");
                errorToastMessage = e.getMessage();
            }

            return token;
        }

        @Override
        protected void onPostExecute(Token token) {
            super.onPostExecute(token);

            if(!Util.isEmpty(errorToastMessage)) {
                Toast.makeText(RegisterActivity.this, errorToastMessage, Toast.LENGTH_LONG).show();
                errorToastMessage = "";
            }

            if(token != null) {
                // We can store token if we want to manage session.
                // Not required in this app
                Log.i("my-app", token.getAccessToken());
                Log.i("my-app", token.getExpireIn().toString());
                Log.i("my-app", token.getRefreshToken());

                Intent intent = new Intent(getApplicationContext(), BusinessListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }

            submitBtn.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        userDAO = new UserDAO();
        errorToastMessage = "";

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder errorStr = new StringBuilder();
                Boolean isError = false;

                if(Util.isEmpty(firstnameInput.getText().toString()) || Util.isEmpty(lastnameInput.getText().toString()) || Util.isEmpty(emailInput.getText().toString()) || Util.isEmpty(passwordInput.getText().toString())) {
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


                if(isError) {
                    Toast.makeText(v.getContext(), errorStr.toString() + "\n", Toast.LENGTH_SHORT).show();
                } else {
                    submitBtn.setEnabled(false);

                    UserRegisterForm userRegisterForm = new UserRegisterForm();
                    userRegisterForm.setEmail(emailInput.getText().toString());
                    userRegisterForm.setPassword(passwordInput.getText().toString());
                    userRegisterForm.setFirstname(firstnameInput.getText().toString());
                    userRegisterForm.setLastname(lastnameInput.getText().toString());

                    new RegisterUser().execute(userRegisterForm);
                }
            }
        });
    }
}
