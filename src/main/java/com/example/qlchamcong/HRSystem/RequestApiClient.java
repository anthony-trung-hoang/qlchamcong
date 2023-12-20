package com.example.qlchamcong.HRSystem;

import com.example.qlchamcong.HRSystem.HttpMethod;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestApiClient {
    private static final String BASE_URL = "http://localhost:3000/api";

    public static <T> T sendRequest(String endpoint, HttpMethod method, String data, Type responseType) {
        StringBuilder response = new StringBuilder();

        try {
            URL apiUrl = new URL(BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            connection.setRequestMethod(method.getMethod());
            connection.setDoOutput(true);

            if (data != null && !data.isEmpty()) {
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = data.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }

                // Convert response data to Java object using Gson
                Gson gson = new Gson();
                T responseObject = gson.fromJson(response.toString(), responseType);
                return responseObject;
            } else {
                // Handle error response
                System.err.println("Error response code: " + responseCode);
                // Consider throwing a custom exception or logging an error message
            }
        } catch (MalformedURLException e) {
            e.printStackTrace(); // Handle or log appropriately
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log appropriately
        }

        return null;
    }
}
