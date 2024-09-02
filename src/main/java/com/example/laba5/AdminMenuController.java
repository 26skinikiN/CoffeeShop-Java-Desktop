package com.example.laba5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminMenuController {
    @FXML
    private Button out;

    @FXML
    private Button workWithProduct;

    @FXML
    private Button workWithUsers;

    @FXML
    void out(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authorisation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @FXML
    void workWithProduct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminProducts.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @FXML
    void workWithUsers(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminFunction.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }
}
