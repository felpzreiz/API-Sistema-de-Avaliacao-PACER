package org.alphacode.pacer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ExecuteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExecuteApplication.class.getResource("/org/alphacode/pacer/home/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
       // stage.setResizable(false);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}