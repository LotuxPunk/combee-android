package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.combee.dataAccess.fromDaoModel.UserConnectForm;
import com.example.combee.dataAccess.fromDaoModel.UserRegisterForm;
import com.example.combee.model.Token;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnectActivity extends AppCompatActivity {

    @BindView(R.id.connect_email_input) EditText emailInput;
    @BindView(R.id.connect_password_input) EditText passwordInput;
    @BindView(R.id.connect_submit_btn) Button submitBtn;

    private UserDAO userDAO;
    private String errorToastMessage;

    private class ConnectUser extends AsyncTask<UserConnectForm, Void, Token> {
        @Override
        protected Token doInBackground(UserConnectForm... userConnectForms) {
            Token token = null;

            try {
                token = userDAO.connect(userConnectForms[0]);
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
                Toast.makeText(ConnectActivity.this, errorToastMessage, Toast.LENGTH_LONG).show();
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
        setContentView(R.layout.activity_connect);

        ButterKnife.bind(this);
        userDAO = new UserDAO();
        errorToastMessage = "";

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.isEmpty(emailInput.getText().toString()) || Util.isEmpty(passwordInput.getText().toString())) {
                    Toast.makeText(v.getContext(), getResources().getString(R.string.please_fill_all), Toast.LENGTH_SHORT).show();
                } else {
                    submitBtn.setEnabled(false);
                    UserConnectForm userConnectForm = new UserConnectForm();
                    userConnectForm.setEmail(emailInput.getText().toString());
                    userConnectForm.setPassword(passwordInput.getText().toString());

                    new ConnectUser().execute(userConnectForm);
                }
            }
        });
    }
}
