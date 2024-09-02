package com.example.laba5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    static Stage mainStage;
    public static int num = 0;
    public static String filename1 = "text.dat";
    public static String filename2 = "users.dat";
    public static ArrayList<User> users = new ArrayList<>(); //для логина
    public static ArrayList<moto> moto2 = new ArrayList<>(); //список для заказа клиента
    public static ArrayList<moto> moto1 = new ArrayList<>();
    public static ArrayList<moto> moto3 = new ArrayList<>();
    private static final double maxWidth = 800;
    private static final double maxHeight = 650;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authorisation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        mainStage = stage;
        mainStage.sizeToScene();
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/image/27.png")));
        mainStage.getIcons().add(icon);
        mainStage.setTitle("CoffeeStation");
        mainStage.setScene(scene);
        stage.setMinWidth(maxWidth);
        stage.setMinHeight(maxHeight);
        stage.show();
        File.readfile(filename2);
        File.readMy(filename1);
    }

    public static void main(String[] args) {
        launch();
    }
    public static void setNewScene(Scene scene) {
        mainStage.sizeToScene();
        mainStage.centerOnScreen();
        mainStage.setScene(scene);
    }
}