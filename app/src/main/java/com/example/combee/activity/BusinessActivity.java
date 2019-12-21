package com.example.combee.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.combee.R;
import com.example.combee.dataAccess.BusinessDAO;
import com.example.combee.model.FullBusiness;
import java.math.BigDecimal;
import java.math.RoundingMode;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BusinessActivity extends AppCompatActivity {
    @BindView(R.id.btn_business_go_to_website) Button goToWebsiteBtn;
    @BindView(R.id.txt_business_name) TextView nameTxt;
    @BindView(R.id.img_business_details) ImageView businessImg;
    @BindView(R.id.txt_business_review) TextView reviewTxt;
    @BindView(R.id.txt_business_locality) TextView localityTxt;
    @BindView(R.id.txt_business_address) TextView addressTxt;

    private BusinessDAO businessDAO;
    private FullBusiness fullBusiness;

    @Override
    protected void onStart() {
        super.onStart();
        new LoadBusiness().execute();
    }

    private class LoadBusiness extends AsyncTask<String, Void, FullBusiness> {
        @Override
        protected FullBusiness doInBackground(String... strings) {
            FullBusiness fullBusiness = null;

            try {
                Intent intent = getIntent();
                Integer id = intent.getIntExtra(getString(R.string.list_to_business_id), 0);
                fullBusiness = businessDAO.GetBusiness(id);
            } catch (Exception e) {
                Log.i("my-app", "ERREUR");
            }

            return fullBusiness;
        }

        @Override
        protected void onPostExecute(FullBusiness business) {
            super.onPostExecute(business);

            fullBusiness = business;
            Glide.with(BusinessActivity.this).load(business.getPicture()).into(businessImg);
            nameTxt.setText(business.getName());
            reviewTxt.setText(business.getAverageReview() == 0.0 ? "Pas de note" : BigDecimal.valueOf(business.getAverageReview()).setScale(2, RoundingMode.HALF_UP).toString());
            localityTxt.setText(business.getLocality() + ", " + business.getZipCode());
            addressTxt.setText(business.getStreet());
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
                if(fullBusiness != null) {
                    Uri uri = Uri.parse(getString(R.string.combee_path) + "business/" + fullBusiness.getId() + "/" + fullBusiness.getName());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
    }
}
