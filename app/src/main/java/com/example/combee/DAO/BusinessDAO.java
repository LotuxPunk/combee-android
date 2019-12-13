package com.example.combee.DAO;

import android.util.Log;

import com.example.combee.DAO.FormDAO.BusinessForm;
import com.example.combee.model.Business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BusinessDAO {

    public static ArrayList<Business> GetAllBusinesses(BusinessForm model) throws Exception {
        // On crée la requete avec les arguments (URL)
        // On fait la tache asyncrhone qui renvoile résultats posey
            // On cast en Business

        URL url = new URL("https://127.0.0.1:5001/api/v1.0/business");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) {
            builder.append(line);
        }

        buffer.close();
        stringJSON = builder.toString();

        Log.i("my-app", stringJSON);

        return new ArrayList<>();
    }
}
