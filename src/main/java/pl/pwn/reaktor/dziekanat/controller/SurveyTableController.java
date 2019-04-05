package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.io.IOException;
import java.util.List;

public class SurveyTableController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<Survey> tvSurvey;

    @FXML
    private TableColumn<Survey, Long> tcId;

    @FXML
    private TableColumn<Survey, String> tcFirstName;

    @FXML
    private TableColumn<Survey, String> tcLastName;

    @FXML
    private TableColumn<Survey, String> tcMail;

    @FXML
    private TableColumn<Survey, String> tcPhone;

    @FXML
    private TableColumn<Survey, Boolean> tcJava;

    @FXML
    private TableColumn<Survey, Boolean> tcPython;

    @FXML
    private TableColumn<Survey, Boolean> tcOther;

    @FXML
    private TableColumn<Survey, String> tcDesc;

    @FXML
    private TableColumn<Survey, String> tcLanguage;

    @FXML
    private TableColumn<Survey, String> tcCourse;

    SurveyService surveyService = new SurveyService();

    @FXML
    void deleteAction(ActionEvent event) {

        if (tvSurvey.getSelectionModel() != null && tvSurvey.getSelectionModel().getSelectedItem() != null) {

            Survey selectedItem = tvSurvey.getSelectionModel()
                    .getSelectedItem();

            surveyService.delete(selectedItem);

            initSurveyTable();
        }

    }

    @FXML
    void backAction(ActionEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("Student View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initialize() {


        initSurveyTable();

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcJava.setCellValueFactory(new PropertyValueFactory<>("java"));
        tcPython.setCellValueFactory(new PropertyValueFactory<>("python"));
        tcOther.setCellValueFactory(new PropertyValueFactory<>("other"));
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("otherDesc"));
        tcLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));


    }

    private void initSurveyTable() {
        List<Survey> surveys = surveyService.getSurveysByStudent(CurrentUser.getCurrentUser()
                .getStudent()
                .getId());

        tvSurvey.setItems(null);

        ObservableList<Survey> observableSurvey = FXCollections.observableArrayList(surveys);
        tvSurvey.setItems(observableSurvey);
    }

}

