package com.example.combee.DAO;

import android.util.Log;

import com.example.combee.DAO.FormDAO.BusinessForm;
import com.example.combee.model.Business;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BusinessDAO {
    public static ArrayList<Business> GetAllBusiness() throws Exception {
        URL url = new URL("https://combeeapi.azurewebsites.net/api/v1.0/business");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) {
            builder.append(line);
            Log.i("my-app", line);
        }

        buffer.close();
        stringJSON = builder.toString();

        return jsonToBusinesses(stringJSON);
    }

    private static ArrayList<Business> jsonToBusinesses(String stringJSON) throws Exception {
        ArrayList<Business> businesses = new ArrayList<>();

        Business business;
        JSONArray jsonArray = new JSONArray((stringJSON));

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonBusiness = jsonArray.getJSONObject(i);

            int id = jsonBusiness.getInt("id");
            String name = jsonBusiness.getString("name");
            String locality = jsonBusiness.getString("locality");
            Double averageReview = jsonBusiness.getDouble("averageReview");

            JSONArray pictureArray = jsonBusiness.getJSONArray("businessPicture");
            String picture = pictureArray.getJSONObject(0) != null ? pictureArray.getJSONObject(0).getString("path") : null;

            business = new Business(id, name, locality, picture, averageReview);
            businesses.add(business);
        }

        return businesses;
    }
}
