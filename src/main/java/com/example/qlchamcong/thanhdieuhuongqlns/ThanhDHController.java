package com.example.qlchamcong.thanhdieuhuongqlns;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ThanhDHController {
    private ThanhDHViewManager theView;
    // maybe have the service if thanhdieuhuong need

    public ThanhDHController(ThanhDHViewManager theView) throws IOException {
        this.theView = theView;

        addToHomeButtonAction();
        addImportDLCCButonAction();
    }

    private void addToHomeButtonAction() throws IOException {
        theView.addHomePageButtonAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.ROUTE_TO_HOME_PAGE));
                try {
                    Parent root = loader.load();
                    theView.displayContentPane(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void addImportDLCCButonAction() throws IOException {
        theView.addImportCCButtonAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(1);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Routes.ROUTE_TO_IMPORT_DLCC));
                try {
                    Parent root = loader.load();
                    theView.displayContentPane(root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
