package com.example.combee.dataAccess;

import android.util.Log;
import com.example.combee.Util;
import com.example.combee.dataAccess.fromDaoModel.BusinessForm;
import com.example.combee.model.Business;
import com.example.combee.model.FullBusiness;
import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BusinessDAO {
    private String queryString = "https://combeeapi.azurewebsites.net/api/v1.0/business";

    public ArrayList<Business> GetAllBusiness(BusinessForm model) throws Exception {

        ArrayList<String> params = new ArrayList<>();

        if(model.getForMan() || model.getForWoman() || model.getForChild()) {
            if(model.getForMan()) params.add("Man=true");
            if(model.getForWoman()) params.add("Woman=true");
            if(model.getForChild()) params.add("Child=true");
        }

        if(model.getPriceOne() || model.getPriceTwo() || model.getPriceThree()) {
            if(model.getPriceOne()) params.add("PriceLow=true");
            if(model.getPriceTwo()) params.add("PriceMedium=true");
            if(model.getPriceThree()) params.add("PriceHigh=true");
        }

        URL url = new URL(queryString + Util.joinedString(params));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) builder.append(line);
        buffer.close();
        stringJSON = builder.toString();

        return jsonToBusinesses(stringJSON);
    }

    private ArrayList<Business> jsonToBusinesses(String stringJSON) throws Exception {
        ArrayList<Business> businesses = new ArrayList<>();

        Business business;
        JSONArray jsonArray = new JSONArray((stringJSON));

        for(int i = 0; i < jsonArray.length(); i++) {
            businesses.add(jsonToBusiness(jsonArray.getJSONObject(i)));
        }

        return businesses;
    }

    public FullBusiness GetBusiness(Integer id) throws Exception {
        URL url = new URL(queryString + "/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        Log.i("my-app", connection.getResponseCode() + "");

        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) builder.append(line);
        buffer.close();
        stringJSON = builder.toString();

        JSONObject jsonBusiness = new JSONObject(stringJSON);

        return jsonToFullBusiness(jsonBusiness);
    }

    private Business jsonToBusiness(JSONObject jsonBusiness) throws Exception {
        Integer id = jsonBusiness.getInt("id");
        String name = jsonBusiness.getString("name");
        String locality = jsonBusiness.getString("locality");
        Double averageReview = jsonBusiness.getDouble("averageReview");
        Integer priceCategory = jsonBusiness.getInt("priceCategory");

        JSONArray pictureArray = jsonBusiness.getJSONArray("businessPicture");
        String picture = pictureArray.getJSONObject(0) != null ? pictureArray.getJSONObject(0).getString("path") : null;

        return new Business(id, name, locality, picture, averageReview, priceCategory);
    }

    private FullBusiness jsonToFullBusiness(JSONObject jsonBusiness) throws Exception {
        Business business = jsonToBusiness(jsonBusiness);

        String description = jsonBusiness.getString("description");
        String street = jsonBusiness.getString("street");
        String zipCode = jsonBusiness.getString("zipCode");

        return new FullBusiness(business.getId(), business.getName(), business.getLocality(), business.getPicture(), business.getAverageReview(), business.getPriceCategory(), description, street, zipCode);
    }
}
