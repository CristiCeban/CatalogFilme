package sample.Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    AnchorPane parent;
    @FXML
    Button btnClose;
    @FXML
    Button btnHome;
    @FXML
    Button btnMovies;
    @FXML
    Button btnGenres;
    @FXML
    Button btnActors;
    @FXML
    Button btnDirectors;
    @FXML
    Button btnExport;

    double x = 0, y = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        exitLabel();
        goHome();
        goMovies();
        goGenres();
        goActors();
        goDirectors();
        goExport();
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
            Platform.exit();
        }));
    }
    public void goHome(){
        btnHome.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Menu.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void goMovies(){
        btnMovies.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Movies.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void goGenres(){
        btnGenres.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Genres.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void goActors(){
        btnActors.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Actors.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void goDirectors(){
        btnDirectors.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Directors.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public void goExport(){
        btnExport.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Export.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}