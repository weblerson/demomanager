package com.lerson.demomanager.application;

import com.lerson.demomanager.utils.FXMLPath;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource(FXMLPath.createFXMLPath("main-view.fxml")));
        Scene scene = new Scene(root.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}