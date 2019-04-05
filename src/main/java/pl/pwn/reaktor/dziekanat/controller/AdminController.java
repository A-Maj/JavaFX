package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SignService;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private TableView<StudentDTO> tvStudents;

    @FXML
    private TableColumn<StudentDTO, Long> colIdS;

    @FXML
    private TableColumn<StudentDTO, String> colLogS;

    @FXML
    private TableColumn<StudentDTO, String> colFirstNameS;

    @FXML
    private TableColumn<StudentDTO, String> colLastNameS;

    @FXML
    private TableColumn<StudentDTO, String> colStreetS;

    @FXML
    private TableColumn<StudentDTO, String> colCityS;

    @FXML
    private TableView<User> tvAdmins;

    @FXML
    private TableColumn<User, Long> colIdA;

    @FXML
    private TableColumn<User, String> colLogA;

    @FXML
    private TableColumn<User, Enum> colRoleA;

    @FXML
    private TableColumn<User, Boolean> colActiveA;

    @FXML
    private MenuItem mLogout;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mAbout;

    private SignService signService = new SignService();

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

    public void initialize() {
        initAdminTable();

        //pobranie danych Studenta z bazy danych

        List<StudentDTO> students = signService.getAllStudents();

        //konwersja na listę zrozumiałą przez TableView z JavaFX;

        ObservableList<StudentDTO> observableStudents = FXCollections.observableArrayList(students);
        tvStudents.setItems(null);
        tvStudents.setItems(observableStudents);

        colIdS.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLogS.setCellValueFactory(new PropertyValueFactory<>("login"));
        colFirstNameS.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastNameS.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStreetS.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCityS.setCellValueFactory(new PropertyValueFactory<>("city"));

    }

    public void initAdminTable() {
        //pobrać dane administratorów z bazy danych
        List<User> admins = signService.getAllAdmins();

       //Konwersja na listę zrozumiałą przez TableView z JavaFX
        ObservableList<User> observableAdmins = FXCollections.observableArrayList(admins);
        tvAdmins.setItems(null);
        tvAdmins.setItems(observableAdmins);

        //ustawianie kolumn które pola z User mają być widoczne i w jakiej kolumnie z widoku.

        colIdA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLogA.setCellValueFactory(new PropertyValueFactory<>("login"));
        colRoleA.setCellValueFactory(new PropertyValueFactory<>("role"));
        colActiveA.setCellValueFactory(new PropertyValueFactory<>("active"));
    }

}

