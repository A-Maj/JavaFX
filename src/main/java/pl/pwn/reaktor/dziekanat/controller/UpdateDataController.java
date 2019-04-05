package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Address;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SignService;
import pl.pwn.reaktor.dziekanat.service.StudentService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateDataController {

    @FXML
    private Label lblId;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tflastName;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfId;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnBack;

    @FXML
    void eventBack(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("User View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void saveEvent(MouseEvent event) {

        User user = CurrentUser.getCurrentUser();

//        Student student = Objects.isNull(user.getStudent()) ? new Student(): user.getStudent();
//        user.setStudent(student);

        StudentService studentService = new StudentService();

        Student student = user.getStudent();
        if(Objects.isNull(student)) {
            student = new Student();
            //zapis do bazy danych

            studentService.save(student);

            user.setStudent(student);

            SignService signService = new SignService();
            signService.update(user);
        }

        student.setFirstName(tfFirstName.getText());
        student.setLastName(tflastName.getText());
        Address address = new Address(tfStreet.getText(), tfCity.getText());

        student.setAddress(address);

        try{
            studentService.update(student);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Success update student");
            info.setTitle("Update student");
            info.show();
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Update student error \n" + e);
            error.setTitle("Error update student");
            error.show();
        }




    }

    public void initialize() {
        User currentUser = CurrentUser.getCurrentUser();

        Optional.ofNullable(currentUser.getStudent())
                .ifPresent(this::mapToForm);
    }

    private void mapToForm(Student s) {
        tfId.setText(String.valueOf(s.getId()));

        tfFirstName.setText(Objects.nonNull(s.getFirstName()) ? s.getFirstName(): "");

        tflastName.setText(Optional.ofNullable(s.getLastName()).orElse(""));

        if(Objects.nonNull(s.getAddress())) {
            tfStreet.setText(Objects.isNull(s.getAddress().getStreet()) ? "": s.getAddress().getStreet());

            tfCity.setText(Optional.ofNullable(s.getAddress().getCity()).orElseGet(() -> ""));
        }

    }

}
