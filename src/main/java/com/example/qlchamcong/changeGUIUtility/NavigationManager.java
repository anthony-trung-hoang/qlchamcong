package com.example.qlchamcong.changeGUIUtility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public void showModal(String modalResource, String title) throws IOException {
        if (contentPane != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(modalResource));
            Parent modalRoot = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle(title);
            modalStage.initOwner(contentPane.getScene().getWindow());
            modalStage.setOnShown(event -> {
                modalStage.setX(
                        contentPane.getScene().getWindow().getX() +
                                contentPane.getScene().getWindow().getWidth() / 2 -
                                modalStage.getWidth() / 2);

                modalStage.setY(
                        contentPane.getScene().getWindow().getY() +
                                contentPane.getScene().getWindow().getHeight() / 2 -
                                modalStage.getHeight() / 2);
            });
            modalStage.setScene(new Scene(modalRoot));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.showAndWait();
        } else {
            System.out.println("Error: contentPane is null. Set contentPane first.");
        }
    }
}

