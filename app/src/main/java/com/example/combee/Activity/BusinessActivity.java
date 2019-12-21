package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.combee.R;
import com.example.combee.dataAccess.BusinessDAO;
import com.example.combee.model.Business;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessActivity extends AppCompatActivity {

    @BindView(R.id.btn_business_go_to_website)
    Button goToWebsiteBtn;

    private BusinessDAO businessDAO;

    @Override
    protected void onStart() {
        super.onStart();
        new LoadBusiness().execute();
    }

    private class LoadBusiness extends AsyncTask<String, Void, Business> {
        @Override
        protected Business doInBackground(String... strings) {
            try {
                businessDAO.GetBusiness(1);
            } catch (Exception e) {
                Log.i("my-app", "ERREUR");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Business business) {
            super.onPostExecute(business);

            Log.i("my-app", "c fait");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        ButterKnife.bind(this);
        businessDAO = new BusinessDAO();

        goToWebsiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://combee.me");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
