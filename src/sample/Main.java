package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    static DataBase db;
static Stage st;
    @Override
    public void start(Stage primaryStage) throws Exception{



//st = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        db = new DataBase("dictionary");
        db.Connect();
        if(db.Connect())
        {
            primaryStage.setTitle("Connected!!!");
        }
        else
        {
            primaryStage.setTitle("ERROR!!!");
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        launch(args);

    }
}
