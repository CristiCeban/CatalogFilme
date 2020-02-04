package sample.Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ClassBusiness.DBController;
import sample.ClassBusiness.Genre;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainGenresController implements Initializable {
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
    @FXML
    TextField filterField;
    @FXML
    TableView<Genre> table;
    @FXML
    TableColumn<Genre, Integer> table_id;
    @FXML
    TableColumn<Genre, String> table_genre;
    @FXML
    TableColumn<Genre, ImageView> table_imagine;


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
        getGenreRow();
        try {
            tableFill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    private void tableFill() throws SQLException {
        table_id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        table_genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        table_imagine.setCellValueFactory(new PropertyValueFactory<>("Image"));
        ObservableList<Genre> observableList= FXCollections.observableArrayList();
        ResultSet resultSet= DBController.getGenres();
        while(resultSet.next()){
            File file = new File(resultSet.getString("image_URL"));
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
            ImageView imageView=new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            observableList.add(new Genre((resultSet.getInt("id")), resultSet.getString("name"),
                    imageView));
        }
        table.setItems(observableList);
        FilteredList<Genre> filteredData = new FilteredList<>(observableList, b->true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(genre -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(genre.getId()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (genre.getGenre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<Genre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    private void getGenreRow(){
        table.setOnMousePressed(((event)-> {
            if(event.getClickCount()==2) {
                Genre genre = table.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Genres-getGenre.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    ResultSet resultSet= DBController.getOneGenre(genre.getId());
                    MainGenresGetGenreController genreController = fxmlLoader.getController();
                    genreController.transferMessage(resultSet);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle("ABC");
                stage.setScene(new Scene(root1));
                stage.show();
            }
        }));
    }
}