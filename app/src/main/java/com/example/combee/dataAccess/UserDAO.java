package com.example.combee.dataAccess;

import android.util.Log;

import com.example.combee.dataAccess.fromDaoModel.UserConnectForm;
import com.example.combee.dataAccess.fromDaoModel.UserRegisterForm;
import com.example.combee.exception.BadLoginException;
import com.example.combee.exception.BadRequestException;
import com.example.combee.exception.UserAlreadyExistException;
import com.example.combee.model.Token;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class UserDAO {

    public Token register(UserRegisterForm model) throws Exception {
        URL url = new URL("https://combeeapi.azurewebsites.net/api/v1.0/user/android/register");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        String jsonInputString = "{ \"Email\": \"" + model.getEmail() + "\" , \"Firstname\": \"" + model.getFirstname() + "\", \"Lastname\": \"" + model.getLastname() + "\", \"Password\": \"" + model.getPassword() + "\" }";

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonInputString);
        writer.flush();
        writer.close();
        os.close();

        int responseCode = connection.getResponseCode();
        switch (responseCode) {
            case HttpsURLConnection.HTTP_CONFLICT:
                Log.i("my-app", "JE PASSE");
                throw new UserAlreadyExistException();
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new BadRequestException();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) builder.append(line);
        buffer.close();
        stringJSON = builder.toString();

        JSONObject token = new JSONObject(stringJSON).getJSONObject("token");

        return jsonToToken(token);
    }

    public Token connect(UserConnectForm model) throws Exception {
        URL url = new URL("https://combeeapi.azurewebsites.net/api/v1.0/user/android/connect");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        String jsonInputString = "{ \"Email\": \"" + model.getEmail() + "\" , \"Password\": \"" + model.getPassword() + "\" }";

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonInputString);
        writer.flush();
        writer.close();
        os.close();

        int responseCode = connection.getResponseCode();
        switch (responseCode) {
            case HttpsURLConnection.HTTP_UNAUTHORIZED:
                throw new BadLoginException();
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new BadRequestException();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String stringJSON = "", line;

        while ((line = buffer.readLine()) != null) builder.append(line);
        buffer.close();
        stringJSON = builder.toString();

        JSONObject token = new JSONObject(stringJSON).getJSONObject("token");

        return jsonToToken(token);
    }

    private Token jsonToToken(JSONObject jsonToken) throws Exception {
        String accessToken = jsonToken.getString("accessToken");
        Integer expireIn = jsonToken.getInt("expireIn");
        String refreshToken = jsonToken.getString("refreshToken");

        return new Token(accessToken, expireIn, refreshToken);
    }

}
