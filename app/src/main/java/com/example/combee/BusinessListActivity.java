package com.example.combee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.User;

public class BusinessListActivity extends AppCompatActivity {

    private class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName;
        public TextView lastName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.textViewFirstName);
            lastName = itemView.findViewById(R.id.textViewLastName);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
        private ArrayList<User> myUsers;

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
            UserViewHolder vh = new UserViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User u = myUsers.get(position);
            holder.firstName.setText(u.getFirstName());
            holder.lastName.setText(u.getLastName());
        }

        @Override
        public int getItemCount() {
            return myUsers == null ? 0 : myUsers.size();
        }

        public void setUsers(ArrayList<User> myUsers) {
            this.myUsers = myUsers;
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

        UserAdapter adapter = new UserAdapter();
        adapter.setUsers(User.createUserList(5));
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
