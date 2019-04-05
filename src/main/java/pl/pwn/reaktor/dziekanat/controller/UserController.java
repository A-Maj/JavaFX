package pl.pwn.reaktor.dziekanat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;

import java.io.IOException;

public class UserController {

    @FXML
    private Button btnUpdateData;

    @FXML
    private Label lblLogin;

    @FXML
    private Button btnSurvey;

    @FXML
    private Button btnShowSurveys;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mClose;

    @FXML
    void aboutAction(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Information about this application");
        info.setHeaderText("Information");
        info.setTitle("About");
        info.show();
    }

    @FXML
    void closeAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logoutAction(ActionEvent event) throws IOException {
        CurrentUser.clean();

        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private MenuItem mAbout;

    @FXML
    void showSurveyAction(ActionEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/surveyTableView.fxml"));
        primaryStage.setTitle("Survey Table");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void surveyEvent(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/surveyView.fxml"));
        primaryStage.setTitle("Ankieta");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void UpdateDataEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/updateDataView.fxml"));
        primaryStage.setTitle("Update");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initialize() {
        lblLogin.setText(lblLogin.getText() + CurrentUser.getCurrentUser().getLogin());
    }

}

