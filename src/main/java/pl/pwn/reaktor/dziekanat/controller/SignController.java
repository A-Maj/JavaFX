package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.service.SignService;

import java.io.IOException;

public class SignController {

    @FXML
    private TextField tfLogin;

    @FXML
    private TextField tfPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Button btnRegister;

    @FXML
    void registerEvent(MouseEvent event) throws IOException {
//        String login = tfLogin.getText();
//        String pass = tfPassword.getText();

        SignService signService = new SignService();
//        User user = signService.saveUser(login, pass);

        signService.saveUser(tfLogin.getText(),pfPassword.getText());
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}

