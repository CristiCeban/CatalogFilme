package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ClassBusiness.DBController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainDirectorsAddDirectorController implements Initializable {
    private String imagePath=new String("");
    @FXML
    AnchorPane parent;
    @FXML
    Button btnClose;
    @FXML
    Button btnAddDirector;
    @FXML
    Button btnPhoto;
    @FXML
    TextField textName;
    @FXML
    TextField textYear;
    @FXML
    TextField textDescription;


    private double x = 0, y = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        exitLabel();
        selectImage();
        addDirector();
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
        btnClose.setOnMousePressed(((event)->{
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Directors.fxml"));
            Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
        }));
    }
    private void addDirector(){
        btnAddDirector.setOnMousePressed(((event)->{
            if(textName.getText().isEmpty()){
                getLabel("Write Director name!");
            }
            else if(textYear.getText().isEmpty()){
                getLabel("Write birthday year of Director!");
            }
            else if(textDescription.getText().isEmpty()){
                getLabel("Write something about Director!");
            }
            else{
                try {
                    DBController.addDirectorWithPhoto(textName.getText(),textDescription.getText(),imagePath,Integer.parseInt(textYear.getText()));
                    getLabel("Director was added!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
    private void selectImage(){
        btnPhoto.setOnMousePressed(((event)->{
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(//
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            imagePath=selectedFile.getAbsolutePath().replace("\\", "\\\\");
            if (!imagePath.equals("")){
                btnPhoto.setText("Photo was choosed");
            }
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
}
