package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ClassBusiness.DBController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    @FXML
    HBox parent;
    @FXML
    Label LabelExitPrincipal;
    @FXML
    javafx.scene.control.TextField TextUserPrincipal;
    @FXML
    PasswordField PassPasswordPrincipal;
    @FXML
    Button ButtonLoginPrincipal;
    @FXML
    Hyperlink hyperlinkNewUser;
    @FXML
    Hyperlink hyperlinkForgotPass;

    double x = 0, y = 0;
    @Override
    public void initialize(URL url,ResourceBundle rb) {
        makeDragable();
        exitLabel();
        newUserHyper();
        login();
        forgotPass();
    }

    private void makeDragable(){
        parent.setOnMousePressed(((event)->{
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        parent.setOnMouseDragged(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        }));

        parent.setOnDragDone(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent.setOnMouseReleased(((event)->{
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));
    }
    public void exitLabel(){
        LabelExitPrincipal.setOnMousePressed(((event)->{
            Platform.exit();
        }));
    }
    @FXML
    public void login(){
        ButtonLoginPrincipal.setOnMousePressed(((mouseEvent) -> {
            if(TextUserPrincipal.getText().isEmpty()){
                getLabel("Username field is empty!");
            }
            else if(PassPasswordPrincipal.getText().isEmpty()){
                getLabel("Password field is empty");
            }
            else {
                try {
                    if(!DBController.logUtil(TextUserPrincipal.getText(),PassPasswordPrincipal.getText())){
                        getLabel("Incorect account");
                    }
                    else{
                        try {
                            Stage stage = (Stage) ButtonLoginPrincipal.getScene().getWindow();
                            stage.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Menu.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();
                            stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setTitle("ABC");
                            stage.setScene(new Scene(root1));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    public void newUserHyper(){
        hyperlinkNewUser.setOnMousePressed(((event)->{
            try {
                HBox hbox= FXMLLoader.load(getClass().getResource("../View/New-User.fxml"));
                parent.getChildren().setAll(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    };
    public void forgotPass(){
        hyperlinkForgotPass.setOnMousePressed(((event)->{
            try {
                HBox hbox= FXMLLoader.load(getClass().getResource("../View/Login-ResetPass.fxml"));
                parent.getChildren().setAll(hbox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    };
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

}
