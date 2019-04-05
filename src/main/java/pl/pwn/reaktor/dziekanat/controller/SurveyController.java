package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class SurveyController {

    @FXML
    private MenuItem mSaveToFile;

    @FXML
    private MenuItem mSaveToDatabase;

    @FXML
    private MenuItem mClear;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mAbout;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfPhone;

    @FXML
    private CheckBox chboxPython;

    @FXML
    private CheckBox chboxJava;

    @FXML
    private CheckBox chboxOther;

    @FXML
    private TextField tfOther;

    @FXML
    private RadioButton rbBasic;

    @FXML
    private ToggleGroup g1;

    @FXML
    private RadioButton rbItermediate;

    @FXML
    private RadioButton rbAdvanced;

    @FXML
    private ComboBox<String> cbCourseSelection;

    @FXML
    private TextArea taPreview;

    @FXML
    private Button btnBack;

    @FXML
    void backAction(ActionEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/userView.fxml"));
        primaryStage.setTitle("Student View");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void ClearAction(ActionEvent event) {

        tfName.clear();
        tfLastName.clear();
        tfMail.clear();
        tfPhone.clear();
        chboxJava.setSelected(false);
        chboxPython.setSelected(false);
        chboxOther.setSelected(false);
        tfOther.clear();
        tfOther.setDisable(true);
        rbItermediate.setSelected(true);
        cbCourseSelection.setValue(null);
        taPreview.clear();


    }

    @FXML
    void CloseAction(ActionEvent event) {

    }

    @FXML
    void SaveToDatabase(ActionEvent event) {

        String name = tfName.getText();
        String surname = tfLastName.getText();
        String mail = tfMail.getText();
        String phone = tfPhone.getText();
        Boolean java = chboxJava.isSelected();
        Boolean python = chboxPython.isSelected();
        Boolean other = chboxOther.isSelected();
        String otherDesc = chboxOther.isSelected() ? tfOther.getText() : "";
        String language = "";

        if(rbBasic.isSelected()) {
            language = rbBasic.getText();
        }
        if(rbItermediate.isSelected()) {
            language = rbItermediate.getText();
        }
        if(rbAdvanced.isSelected()) {
            language = rbAdvanced.getText();
        }

        String course = cbCourseSelection.getValue();
        Survey survey = new Survey(name, surname, mail, phone, java, python, other, otherDesc, language, course, CurrentUser.getCurrentUser().getStudent().getId());

        SurveyService surveyService = new SurveyService();

        surveyService.save(survey);


    }

    @FXML
    void SaveToFileAction(ActionEvent event) {

        if(isNotCompleted()) {
            showAlertNotComplete();
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save resource file.");
            File file = fileChooser.showSaveDialog(null);
            String filePath = file.getPath();

            try (PrintWriter save = new PrintWriter(filePath)) {
                save.println(getSurveyText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    @FXML
    void otherAction(MouseEvent event) {

        if(chboxOther.isSelected()) {
            tfOther.setDisable(false);
            tfOther.setEditable(true);
        } else {
            tfOther.setDisable(true);
            tfOther.clear();
        }

    }

    @FXML
    void previewEvent(MouseEvent event) {

        if(isNotCompleted()) {
            showAlertNotComplete();
            return;
        }

        String value = getSurveyText();
        taPreview.setText(value);

    }

    private String getSurveyText() {
        String name = tfName.getText();
        String surname = tfLastName.getText();
        String mail = tfMail.getText();
        String phone = tfPhone.getText();
        String java = chboxJava.isSelected() ? chboxJava.getText() + "\n" : "";
        String python = chboxPython.isSelected() ? chboxPython.getText() + "\n"  : "";
        String other = chboxOther.isSelected() ? tfOther.getText() + "\n" : "";

        String language = "";

        if(rbBasic.isSelected()) {
            language = rbBasic.getText();
        }
        if(rbItermediate.isSelected()) {
            language = rbItermediate.getText();
        }
        if(rbAdvanced.isSelected()) {
            language = rbAdvanced.getText();
        }

        String course = cbCourseSelection.getValue();

        return "Name: " + name + "\n" + "Last name: " + surname + "\n" + "Mail: " +
                mail + "\n" + "Phone: " + phone + "\n" + "Languages: " + "\n" + java + python + other + "English skill: " + language + "\n" + "Course: " + course;
    }

    private void showAlertNotComplete() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText("Please fill out the form!");
        info.setTitle("Empty Fields.");
        info.show();
    }

    private boolean isNotCompleted() {
        return "".equals(tfName.getText()) || "".equals(tfLastName.getText()) || "".equals(tfMail.getText()) || "".equals(tfPhone.getText())
                || (!chboxJava.isSelected() && !chboxPython.isSelected() && "".equals(tfOther.getText())) || cbCourseSelection.getValue() == null;
    }

    public void initialize() {
        ObservableList<String> courses = FXCollections.observableArrayList("Back-end", "Front-end", "Python", "Java");
        cbCourseSelection.setItems(courses);
    }

}
