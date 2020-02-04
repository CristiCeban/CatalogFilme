package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import sample.ClassBusiness.DBController;

import static javafx.application.Platform.exit;

public class LoginControllerNewUser implements Initializable{


    @FXML
    HBox parent1;
    @FXML
    TextField user;
    @FXML
    PasswordField pass1;
    @FXML
    PasswordField pass2;
    @FXML
    TextField email;
    @FXML
    Button btn;
    @FXML
    Label LabelExitPrincipal;
    @FXML
    Label labelBack;


    double x = 0, y = 0;
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        makeDragable();
        addUser();
        exitLabel();
        goBack();
    }

    private void makeDragable(){
        parent1.setOnMousePressed(((event)->{
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent1.setOnMouseDragged(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));

        parent1.setOnDragDone(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent1.setOnMouseReleased(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
    }
    private void getLabel(String s){
        Label secondLabel = new Label(s);

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 220, 110);

        Stage secondStage = new Stage();
        secondStage.setTitle("Alert!");
        secondStage.setScene(secondScene);
        secondStage.show();
    }
    public void addUser(){
        btn.setOnMousePressed(((event) -> {
                if(user.getText().isEmpty()){
                    getLabel("Write your Username!");
                }
                else if (email.getText().isEmpty()){
                    getLabel("Write your Email!");
                }
                else if (pass1.getText().isEmpty()){
                    getLabel("Write your Password!");
                }
                else if (pass2.getText().isEmpty()){
                    getLabel("Write your Password repeatly!");
                }
                else if (pass1.getLength() < 8) {
                    getLabel("To short pass");
                }
                else if ( !pass1.getText().equals(pass2.getText())) {
                    getLabel("Write the same Password!");
                }
                 else {
                     try {
                        if(DBController.addUser(user.getText(),pass1.getText(),email.getText())) {
                            getLabel("Registration with succes!");
                            HBox hbox = FXMLLoader.load(getClass().getResource("../View/Login-Form.fxml"));
                            parent1.getChildren().setAll(hbox);
                        }
                        else {
                            getLabel("Account with this username already exist!");
                        }
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
        }));
    }
    public void exitLabel(){
        LabelExitPrincipal.setOnMousePressed(((event)->{
            exit();
        }));
    }
    public void goBack(){
        labelBack.setOnMousePressed(((event)->{
            try {
                HBox hbox=FXMLLoader.load(getClass().getResource("../View/Login-Form.fxml"));
                parent1.getChildren().setAll(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
