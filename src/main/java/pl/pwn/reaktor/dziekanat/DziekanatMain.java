package pl.pwn.reaktor.dziekanat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class DziekanatMain extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        primaryStage.setTitle("Dziekanat");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        DziekanatMain.primaryStage = primaryStage;
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Metoda init, uruchomiana tylko raz przy starcie projektu");
        HibernateUtils.initSessionFactory();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Metoda stop, uruchomiana tylko raz przy zamykaniu projektu");
        HibernateUtils.destroySessionFactory();
    }
}
