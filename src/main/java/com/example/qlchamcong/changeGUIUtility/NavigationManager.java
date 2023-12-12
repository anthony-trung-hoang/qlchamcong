package com.example.qlchamcong.changeGUIUtility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NavigationManager {
    private static NavigationManager instance;
    private Pane contentPane;

    private NavigationManager() {
        // private constructor to prevent instantiation
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

