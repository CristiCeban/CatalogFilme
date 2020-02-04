package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.ClassBusiness.DBController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainMoviesGetMovieController implements Initializable {
    @FXML
    AnchorPane parent;
    @FXML
    Button btnClose;
    @FXML
    ImageView imageview;
    @FXML
    TextField text_title;
    @FXML
    TextField text_actors;
    @FXML
    TextField text_directors;
    @FXML
    TextField text_year;
    @FXML
    TextField text_certificate;
    @FXML
    TextField text_imdb;
    @FXML
    TextField text_runtime;
    @FXML
    TextField text_metascore;
    @FXML
    TextField text_gross;
    @FXML
    TextField text_votes;
    @FXML
    TextField text_genre;
    @FXML
    TextFlow text_description;
    @FXML
    TextField text_moviescore;
    private ResultSet resultSet;
    private double x = 0, y = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        exitLabel();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/Main-Movies.fxml"));
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
    private void tableFill() throws SQLException {
        if (resultSet.next()) {
            File file = new File(resultSet.getString("image_URL"));
            javafx.scene.image.Image image = new javafx.scene.image.Image(file.toURI().toString());
            imageview.setImage(image);
            imageview.setFitHeight(100);
            imageview.setFitWidth(100);
            text_title.setText(resultSet.getString("title"));
            text_year.setText(resultSet.getString("year"));
            text_certificate.setText(resultSet.getString("certificate"));
            text_imdb.setText(resultSet.getString("imdb_rating"));
            text_runtime.setText(resultSet.getString("runtime"));
            Text t1 = new Text(resultSet.getString("description"));
            t1.setFont(Font.font("Regular", 14));
            text_description.getChildren().add(t1);
            text_metascore.setText(resultSet.getString("metascore"));
            text_votes.setText(resultSet.getString("votes"));
            text_gross.setText(resultSet.getString("gross")+" $");
            text_moviescore.setText(resultSet.getString("moviescol"));
            ResultSet resultSet1=DBController.getStarFromMovie(resultSet.getInt("id"));
            StringBuilder string1= new StringBuilder();
            while(resultSet1.next()){
                string1.append(resultSet1.getString("name")).append(",");
            }
            text_actors.setText(string1.toString());
            string1 = new StringBuilder();
            resultSet1=DBController.getDirectorFromMovie(resultSet.getInt("id"));
            while(resultSet1.next()){
                string1.append(resultSet1.getString("name")).append(",");
            }
            text_directors.setText(string1.toString());
            string1 = new StringBuilder();
            resultSet1=DBController.getGenreFromMovie(resultSet.getInt("id"));
            while(resultSet1.next()){
                string1.append(resultSet1.getString("name")).append(",");
            }
            text_genre.setText(string1.toString());
        }
    }
    void transferMessage(ResultSet rs) throws SQLException {
        resultSet = rs;
        tableFill();
    }
}
