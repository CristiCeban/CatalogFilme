package sample.ClassBusiness;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        DBController.connect();
        Parent root = FXMLLoader.load(getClass().getResource("../View/Login-Form.fxml"));

        Scene scene=new Scene(root);

        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);

        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}