package com.example.apifx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Controller {

    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        String catFact = fetchCatFact();
        welcomeText.setText(catFact);
    }

    private String fetchCatFact() {
        StringBuilder factBuilder = new StringBuilder();
        try {
            URL url = new URL("https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                factBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(factBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("all");
            JSONObject factObject = jsonArray.getJSONObject(0);
            return factObject.getString("text");
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch cat fact";
        }
    }
}
