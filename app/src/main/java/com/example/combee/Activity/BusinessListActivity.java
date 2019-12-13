package com.example.combee.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
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

import com.example.combee.DAO.BusinessDAO;
import com.example.combee.DAO.FormDAO.BusinessForm;
import com.example.combee.R;
import com.example.combee.model.Business;


public class BusinessListActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

        new LoadPerson().execute();
    }

    private class LoadPerson extends AsyncTask<String, Void, ArrayList<Business>> {
        @Override
        protected ArrayList<Business> doInBackground(String... strings) {
            Log.i("my-app", "ICI");

            SharedPreferences sharedPref =  getSharedPreferences(getString(R.string.saved_preference_file), Context.MODE_PRIVATE);

            BusinessForm businessForm = new BusinessForm();
            businessForm.setForMan(sharedPref.getBoolean(getString(R.string.saved_gender_man), false));
            businessForm.setForWoman(sharedPref.getBoolean(getString(R.string.saved_gender_woman), false));
            businessForm.setForChild(sharedPref.getBoolean(getString(R.string.saved_gender_child), false));

            businessForm.setPriceOne(sharedPref.getBoolean(getString(R.string.saved_price_one), false));
            businessForm.setPriceTwo(sharedPref.getBoolean(getString(R.string.saved_price_two), false));
            businessForm.setPriceThree(sharedPref.getBoolean(getString(R.string.saved_price_three), false));

            ArrayList<Business> t = new ArrayList<>();
            try {
                t = BusinessDAO.GetAllBusinesses(businessForm);
            } catch (Exception e) {
                Log.i("my-app", e.toString());
            }

            return t;
        }

        @Override
        protected void onPostExecute(ArrayList<Business> businesses) {
            super.onPostExecute(businesses);

            Log.i("my-app", "c fait");
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Integer position);
    }

    private class BusinessViewHolder extends RecyclerView.ViewHolder {
        public TextView txtBusinessName;
        public TextView txtBusinessLocality;
        public ImageView imgViewPicture;

        public BusinessViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
            super(itemView);
            txtBusinessName = itemView.findViewById(R.id.txtBusinessListName);
            txtBusinessLocality = itemView.findViewById(R.id.txtBusinessListLocality);
            imgViewPicture = itemView.findViewById(R.id.imgViewPicture);

            itemView.setOnClickListener(e -> {
                int currentPosition = getAdapterPosition();
                listener.onItemSelected(currentPosition);
            });
        }
    }

    private class BusinessAdapter extends RecyclerView.Adapter<BusinessViewHolder> {
        private ArrayList<Business> myBusinesses;

        @NonNull
        @Override
        public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
            BusinessViewHolder vh = new BusinessViewHolder(v, position -> {
                // MAUVAIS : BERK
                Business touchedBusiness = Business.getAll().get(position);

                Intent intent = new Intent(parent.getContext(), BusinessActivity.class);
                intent.putExtra("BUSINESS_ID", touchedBusiness.getId());
                startActivity(intent);
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
            Business u = myBusinesses.get(position);
            holder.txtBusinessName.setText(u.getName());
            holder.txtBusinessLocality.setText(u.getLocality());

            Glide.with(holder.itemView.getContext()).load("https://lepetitjournal.com/sites/default/files/styles/main_article/public/2019-08/keren-perez-LpKso44P3eg-unsplash.jpg?itok=kJq2IGVr").into(holder.imgViewPicture);
        }

        @Override
        public int getItemCount() {
            return myBusinesses == null ? 0 : myBusinesses.size();
        }

        public void setUsers(ArrayList<Business> myBusinesses) {
            this.myBusinesses = myBusinesses;
            notifyDataSetChanged();
        }
    }

    @BindView(R.id.rvContacts) RecyclerView businessListView;
    @BindView(R.id.btn_price_settings) Button btnPriceSettings;
    @BindView(R.id.btn_gender_settings) Button btnGenderSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        ButterKnife.bind(this);

        BusinessAdapter adapter = new BusinessAdapter();
        adapter.setUsers(Business.getAll());
        businessListView.setLayoutManager(new LinearLayoutManager(this));
        businessListView.setAdapter(adapter);

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
