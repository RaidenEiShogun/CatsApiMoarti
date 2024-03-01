package com.example.apifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CatsApli extends Application {

    @Override
    public void start(Stage stage) {
        
        Label textOne = new Label();
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> textOne.setText(fetchCatFact()));

        VBox root = new VBox(10);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        root.getChildren().addAll(textOne, refreshButton);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Cat Facts");
        stage.show();

        textOne.setText(fetchCatFact());
    }

    private String fetchCatFact() {
        StringBuilder factBuilder = new StringBuilder();
        try {
            URL url = new URL("https://catfact.ninja/fact?max_length=140");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                factBuilder.append(line);
            }
            reader.close();

            // json в студию
            System.out.println("Received JSON: " + factBuilder);

            JSONObject jsonObject = new JSONObject(factBuilder.toString());
            return jsonObject.getString("fact");

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch cat fact";
        } catch (JSONException e) {
            e.printStackTrace();
            return "Invalid JSON response";
        }
    }




    public static void main(String[] args) {
        launch();
    }
}
