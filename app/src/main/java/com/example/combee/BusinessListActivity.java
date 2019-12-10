package com.example.combee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import model.Business;


public class BusinessListActivity extends AppCompatActivity {

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
