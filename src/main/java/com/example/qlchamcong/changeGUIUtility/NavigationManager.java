package com.example.qlchamcong.changeGUIUtility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NavigationManager {
    private static NavigationManager instance;
    private Pane contentPane;

    private Map<String, Object> sharedDataMap;

    private NavigationManager() {
        sharedDataMap = new HashMap<>();
    }

    public static NavigationManager getInstance() {
        if (instance == null) {
            instance = new NavigationManager();
        }
        return instance;
    }

    public void setContentPane(Pane contentPane) {
        this.contentPane = contentPane;
    }


    public Map<String, Object> getSharedDataMap() {
        return sharedDataMap;
    }

    public void setSharedDataMap(Map<String, Object> sharedDataMap) {
        this.sharedDataMap = sharedDataMap;
    }

    public void setSharedData(String key, Object sharedData) {
        sharedDataMap.put(key, sharedData);
    }

    public Object getSharedData(String key) {
        return sharedDataMap.get(key);
    }

    public void changeGUI(String viewResource) throws IOException {
        if (contentPane != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewResource));
            Parent root = loader.load();

            contentPane.getChildren().setAll(root);
        } else {
            System.out.println("Error: contentPane is null. Set contentPane first.");
        }
    }
}

