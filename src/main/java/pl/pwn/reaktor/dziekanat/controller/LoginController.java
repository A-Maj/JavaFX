package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.LoginService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button btnShow;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnSign;

    @FXML
    private TextField tfPassword;

    @FXML
    private PasswordField pfPassword;

    @FXML
    void enterEvent(KeyEvent event) throws IOException {

        if(KeyCode.ENTER.equals(event.getCode())) {

            loginEvent(event);
        }

    }

    @FXML
    void guestEvent(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/view/guestView.fxml"));
        primaryStage.setTitle("Guest View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void loginEvent(InputEvent event) throws IOException {

        String login = tfLogin.getText();
        String pass = pfPassword.isVisible() ? pfPassword.getText() : tfPassword.getText();

        LoginService loginService = new LoginService();
        User user = loginService.login(login, pass);

        if(user!=null) {
            RoleEnum role = user.getRole();
            System.out.println("Zalogowano użytkownika: " + login + " o roli: " + role);
            CurrentUser.setCurrentUser(user);

            if(RoleEnum.ROLE_STUDENT.equals(role)) {

                Stage primaryStage = DziekanatMain.getPrimaryStage();

                Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
                primaryStage.setTitle("User View");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }

            if(RoleEnum.ROLE_ADMIN.equals(role)) {

                Stage primaryStage = DziekanatMain.getPrimaryStage();

                Parent root = FXMLLoader.load(getClass().getResource("/view/adminView.fxml"));
                primaryStage.setTitle("Admin View");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    @FXML
    void showEvent(MouseEvent event) {

        if ("Show".equalsIgnoreCase(btnShow.getText())){

            tfPassword.setText(pfPassword.getText());
            tfPassword.setVisible(true);
            pfPassword.setVisible(false);
            btnShow.setText("Hide");
        } else if ("Hide".equalsIgnoreCase(btnShow.getText())) {
            pfPassword.setText(tfPassword.getText());
            pfPassword.setVisible(true);
            tfPassword.setVisible(false);
            btnShow.setText("Show");
        }

    }

    @FXML
    void signEvent(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/view/signView.fxml"));
        primaryStage.setTitle("Sign-in View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

}


