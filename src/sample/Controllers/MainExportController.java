package sample.Controllers;

import com.itextpdf.text.DocumentException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.ClassBusiness.ExportController;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainExportController implements Initializable {
    @FXML
    AnchorPane parent;
    @FXML
    ComboBox<String> box_rating;
    @FXML
    ComboBox<String> box_type;
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
    @FXML
    Button btnExportFile;

    private double x = 0, y = 0;
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
        export();
        fillBox();
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
    private void goHome(){
        btnHome.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Menu.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void goMovies(){
        btnMovies.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Movies.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void goGenres(){
        btnGenres.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Genres.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void goActors(){
        btnActors.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Actors.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void goDirectors(){
        btnDirectors.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Directors.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void goExport(){
        btnExport.setOnMousePressed(((event)->{
            try {
                AnchorPane anchorPane= FXMLLoader.load(getClass().getResource("../View/Main-Export.fxml"));
                parent.getChildren().setAll(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    private void fillBox(){
        String[] orderBy ={"Ordonare dupa Rating","Ordonare dupa Nume"};
        box_rating.setItems(FXCollections.observableArrayList(orderBy));
        box_rating.setValue(orderBy[0]);
        String[] fileType ={"PDF","HTML"};
        box_type.setItems(FXCollections.observableArrayList(fileType));
        box_type.setValue(fileType[0]);
    }

    public void export(){
        btnExportFile.setOnMousePressed(((event)->{
            if(box_type.getSelectionModel().getSelectedItem().equals("PDF")) {
                if (box_rating.getSelectionModel().getSelectedItem().equals("Ordonare dupa Rating")) {
                    try {
                        ExportController.exportPDFRating();
                        getLabel();
                    } catch (DocumentException | IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if(box_rating.getSelectionModel().getSelectedItem().equals("Ordonare dupa Nume")){
                    try {
                        ExportController.exportPDFNume();
                        getLabel();
                    } catch (DocumentException | IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            else if(box_type.getSelectionModel().getSelectedItem().equals("HTML")){
                if(box_rating.getSelectionModel().getSelectedItem().equals("Ordonare dupa Rating")) {
                    try {
                        ExportController.exportHTMLRating();
                        getLabel();
                    } catch (DocumentException | SQLException | IOException | ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                }
                else if(box_rating.getSelectionModel().getSelectedItem().equals("Ordonare dupa Nume")){
                    try {
                        ExportController.exportHTMLNume();
                        getLabel();
                    } catch (DocumentException | IOException | SQLException | ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                }

            }
        }));
    }
    private void getLabel(){
        Label secondLabel = new Label("Export successfully");
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 220, 110);
        Stage secondStage = new Stage();
        secondStage.setTitle("Alert!");
        secondStage.setScene(secondScene);
        secondStage.show();
    }
}