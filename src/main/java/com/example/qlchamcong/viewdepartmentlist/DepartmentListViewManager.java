package com.example.qlchamcong.viewdepartmentlist;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class DepartmentListViewManager implements Initializable {
    @FXML
    public TableView<AttendanceRecord> departmentListTableView;
    @FXML

    public TableColumn<AttendanceRecord, Integer> departmentId;

    @FXML
    public TableColumn<AttendanceRecord, String> departmentName;
    @FXML
    public TableColumn<AttendanceRecord, Timestamp> founding;
    @FXML
    public TableColumn<AttendanceRecord, String> numberStaff;
    @FXML
    public TableColumn<AttendanceRecord, String> leader;
    @FXML
    public TableColumn<AttendanceRecord, Void> action;
    //    @FXML
//    public Label pageTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        DepartmentListController departmentListController = new DepartmentListController(navUtil, argumentUtil);

//        int shareData = departmentListController.getEmployeeId();
//        this.pageTitle.setText(shareData +"");

//        fetchAndDisplayTableData();
    }

    public void fetchAndDisplayTableData() {
//        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
//        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTime()));
//        timeStampColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTimestamp()));
//        timeKeeperIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTimeKeeperId()).asObject());
//        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

//        viewDetailsColumn.setCellFactory(param -> new TableCell<>() {
//            private final HBox buttonsContainer = new HBox();
//            private final Button updateButton = new Button("Update");
//            private final Button deleteButton = new Button("Delete");
//
//            {
//                buttonsContainer.getChildren().addAll(updateButton, deleteButton);
//
//                updateButton.setOnAction(event -> {
//                    System.out.println(getTableView().getItems().get(getIndex()));
//                    try {
//                        showUpdateModal();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(buttonsContainer);
//                }
//            }
//        });


//        List<AttendanceRecord> attendanceRecordList = departmentListController.fetchListOfRecords(1, new Date());
//        System.out.println(attendanceRecordList.size());
//        ObservableList<AttendanceRecord> observableList = FXCollections.observableArrayList(attendanceRecordList);
//        departmentListTableView.setItems(observableList);
    }

}
