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
import sample.ClassBusiness.Movies;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMoviesController implements Initializable {
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
    Button btnAddMovie;
    @FXML
    TextField filterField;
    @FXML
    TableView<Movies> table;
    @FXML
    TableColumn<Movies, Integer> table_id;
    @FXML
    TableColumn<Movies, String> table_title;
    @FXML
    TableColumn<Movies, Integer> table_year;
    @FXML
    TableColumn<Movies, String> table_certificate;
    @FXML
    TableColumn<Movies, Integer> table_run_Time;
    @FXML
    TableColumn<Movies, Double> table_IMDB_Rating;
    @FXML
    TableColumn<Movies, String> table_description;
    @FXML
    TableColumn<Movies, ImageView> table_image;
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
        addMovie();
        getMovieRow();
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
        table_title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        table_year.setCellValueFactory(new PropertyValueFactory<>("Year"));
        table_certificate.setCellValueFactory(new PropertyValueFactory<>("Certificate"));
        table_run_Time.setCellValueFactory(new PropertyValueFactory<>("runtime"));
        table_IMDB_Rating.setCellValueFactory(new PropertyValueFactory<>("imdb_Rating"));
        table_description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        table_image.setCellValueFactory(new PropertyValueFactory<>("Image"));
        ObservableList<Movies> observableList= FXCollections.observableArrayList();
        ResultSet resultSet= DBController.getMovies();
        while(resultSet.next()){
            File file = new File(resultSet.getString("image_URL"));
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
            ImageView imageView=new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            observableList.add(new Movies((resultSet.getInt("id")), resultSet.getString("title"),
                    resultSet.getInt("year"),imageView,resultSet.getString("certificate"),
                    resultSet.getInt("runtime"),resultSet.getDouble("imdb_rating"),
                    resultSet.getString("description")));
        }
        table.setItems(observableList);
        FilteredList<Movies> filteredData = new FilteredList<>(observableList,b->true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(movies -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                    if (Integer.toString(movies.getId()).toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if (movies.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Integer.toString(movies.getYear()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (movies.getCertificate().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Integer.toString(movies.getRuntime()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (Double.toString(movies.getImdb_Rating()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (movies.getCertificate().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else return movies.getDescription().toLowerCase().contains(lowerCaseFilter);
            });
        });
        SortedList<Movies> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    private void addMovie(){
        btnAddMovie.setOnMousePressed(((event)->{
            try {
                Stage stage = (Stage) btnAddMovie.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Movies-addMovie.fxml"));
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
            try {
                tableFill();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }
    private void getMovieRow(){
        table.setOnMousePressed(((event)-> {
            if(event.getClickCount()==2) {
                Movies movie = table.getSelectionModel().getSelectedItem();
                Stage stage = (Stage) btnAddMovie.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Movies-getMovie.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    ResultSet resultSet= DBController.getOneMovie(movie.getId());
                    MainMoviesGetMovieController movieController = fxmlLoader.getController();
                    movieController.transferMessage(resultSet);
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