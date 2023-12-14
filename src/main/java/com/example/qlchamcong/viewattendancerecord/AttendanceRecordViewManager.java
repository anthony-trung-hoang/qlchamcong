package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.*;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceRecordViewManager implements Initializable {
    @FXML
    public TableView<AttendanceRecord> attendanceRecordTableView;
    @FXML

    public TableColumn<AttendanceRecord, Integer> idColumn;

    @FXML
    public TableColumn<AttendanceRecord, String> timeColumn;
    @FXML
    public TableColumn<AttendanceRecord, Timestamp> timeStampColumn;
    @FXML
    public TableColumn<AttendanceRecord, String> typeColumn;
    @FXML
    public TableColumn<AttendanceRecord, Integer> timeKeeperIdColumn;
    @FXML
    public TableColumn<AttendanceRecord, Void> viewDetailsColumn;
    private AttendanceRecordController attendanceRecordController;
    @FXML
    public Label pageTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        attendanceRecordController = new AttendanceRecordController(navUtil, argumentUtil);

        int shareData = attendanceRecordController.getEmployeeId();
        this.pageTitle.setText(shareData +"");

        fetchAndDisplayTableData();
    }

    public void fetchAndDisplayTableData() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
        timeStampColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTimestamp()));
        timeKeeperIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTimeKeeperId()).asObject());
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        viewDetailsColumn.setCellFactory(param -> new TableCell<>() {
            private final HBox buttonsContainer = new HBox();
            private final Button updateButton = new Button("Update");
            private final Button deleteButton = new Button("Delete");

            {
                buttonsContainer.getChildren().addAll(updateButton, deleteButton);

                updateButton.setOnAction(event -> {
                    System.out.println(getTableView().getItems().get(getIndex()));
                    try {
                        showUpdateModal();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsContainer);
                }
            }
        });


        List<AttendanceRecord> attendanceRecordList = attendanceRecordController.fetchListOfRecords(1, new Date());
        System.out.println(attendanceRecordList.size());
        ObservableList<AttendanceRecord> observableList = FXCollections.observableArrayList(attendanceRecordList);
        attendanceRecordTableView.setItems(observableList);
    }

    public void showUpdateModal() throws IOException {
        attendanceRecordController.showUpdateModal();
    }
}
