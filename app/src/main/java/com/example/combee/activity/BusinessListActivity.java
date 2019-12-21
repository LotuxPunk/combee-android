package com.example.combee.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.combee.R;
import com.example.combee.Util;
import com.example.combee.dataAccess.BusinessDAO;
import com.example.combee.dataAccess.fromDaoModel.BusinessForm;
import com.example.combee.model.Business;


public class BusinessListActivity extends AppCompatActivity {
    @BindView(R.id.rvContacts) RecyclerView businessListView;
    @BindView(R.id.btn_price_settings) Button btnPriceSettings;
    @BindView(R.id.btn_gender_settings) Button btnGenderSettings;

    private ArrayList<Business> businesses;
    private BusinessAdapter businessAdapter;
    private BusinessDAO businessDAO;

    @Override
    protected void onStart() {
        super.onStart();
        new LoadBusinesses().execute();
    }

    private class LoadBusinesses extends AsyncTask<String, Void, ArrayList<Business>> {
        @Override
        protected ArrayList<Business> doInBackground(String... strings) {
            SharedPreferences sharedPref =  getSharedPreferences(getString(R.string.saved_preference_file), Context.MODE_PRIVATE);

            BusinessForm businessForm = new BusinessForm();
            businessForm.setForMan(sharedPref.getBoolean(getString(R.string.saved_gender_man), false));
            businessForm.setForWoman(sharedPref.getBoolean(getString(R.string.saved_gender_woman), false));
            businessForm.setForChild(sharedPref.getBoolean(getString(R.string.saved_gender_child), false));

            businessForm.setPriceOne(sharedPref.getBoolean(getString(R.string.saved_price_one), false));
            businessForm.setPriceTwo(sharedPref.getBoolean(getString(R.string.saved_price_two), false));
            businessForm.setPriceThree(sharedPref.getBoolean(getString(R.string.saved_price_three), false));

            ArrayList<Business> listBusiness = new ArrayList<>();
            try {
                listBusiness = businessDAO.GetAllBusiness(businessForm);
                businesses = listBusiness;
            } catch (Exception e) {
                Log.i("my-app", "ICI");
            }

            return listBusiness;
        }

        @Override
        protected void onPostExecute(ArrayList<Business> listBusiness) {
            super.onPostExecute(listBusiness);
            businessAdapter.notifyDataSetChanged();
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Integer position);
    }

    private class BusinessViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBusinessName;
        public TextView txtBusinessLocality;
        public TextView txtBusinessPrice;
        public ImageView imgViewPicture;

        public BusinessViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
            super(itemView);
            txtBusinessName = itemView.findViewById(R.id.business_list_txt_name);
            txtBusinessLocality = itemView.findViewById(R.id.business_list_txt_locality);
            txtBusinessPrice = itemView.findViewById(R.id.business_list_txt_price);
            imgViewPicture = itemView.findViewById(R.id.imgViewPicture);

            itemView.setOnClickListener(e -> {
                int currentPosition = getAdapterPosition();
                listener.onItemSelected(currentPosition);
            });
        }
    }

    private class BusinessAdapter extends RecyclerView.Adapter<BusinessViewHolder> {
        @NonNull
        @Override
        public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
            BusinessViewHolder vh = new BusinessViewHolder(v, position -> {
                Business touchedBusiness = businesses.get(position);

                Intent intent = new Intent(parent.getContext(), BusinessActivity.class);
                intent.putExtra(getString(R.string.list_to_business_id), touchedBusiness.getId());
                startActivity(intent);
            });

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
            Business b = businesses.get(position);
            holder.txtBusinessName.setText(b.getName());
            holder.txtBusinessLocality.setText(b.getLocality());
            holder.txtBusinessPrice.setText(Util.priceCategoryString(b.getPriceCategory()));
            Glide.with(holder.itemView.getContext()).load(b.getPicture()).into(holder.imgViewPicture);
        }

        @Override
        public int getItemCount() {
            return businesses == null ? 0 : businesses.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        businesses = new ArrayList<>();
        businessAdapter = new BusinessAdapter();
        businessDAO = new BusinessDAO();

        ButterKnife.bind(this);

        businessListView.setLayoutManager(new LinearLayoutManager(this));
        businessListView.setAdapter(businessAdapter);

        btnPriceSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PriceSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnGenderSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GenderSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
