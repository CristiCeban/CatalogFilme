package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.ClassBusiness.DBController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class LoginForgotPassController implements Initializable{


    @FXML
    HBox parent1;
    @FXML
    TextField email;
    @FXML
    Button btn;
    @FXML
    Label LabelExitPrincipal;
    @FXML
    Label labelBack;


    private double x = 0, y = 0;
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        makeDragable();
        exitLabel();
        goBack();
        resetPass();
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
    public void resetPass(){
        btn.setOnMousePressed(((event)->{
            if(!email.getText().isEmpty()){
                try {
                    if(DBController.findEmail(email.getText())){
                        DBController.changePass(email.getText());
                        getLabel("New password was sended to email!");
                    }
                    else{
                        getLabel("User with this email doesn't exist!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                getLabel("write your email!");
            }
        }));
    }
}
