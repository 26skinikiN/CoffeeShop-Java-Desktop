package com.example.laba5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button authSingUpButton;

    @FXML
    private Button loginSingUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    private boolean isDataCorrectt(){
        if(login_field.getLength() == 0 && password_field.getLength() == 0){
            login_field.setBorder(Border.stroke(Color.CRIMSON));
            password_field.setBorder(Border.stroke(Color.CRIMSON));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Заполните поля!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            login_field.setText("");
            password_field.setText("");
            login_field.setBorder(Border.stroke(Color.LIGHTGREY));
            password_field.setBorder(Border.stroke(Color.LIGHTGREY));
            return false;
        }
        if(login_field.getLength() == 0){
            login_field.setBorder(Border.stroke(Color.CRIMSON));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Введите логин!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            login_field.setText("");
            login_field.setBorder(Border.stroke(Color.LIGHTGREY));
            return false;
        }
        if(password_field.getLength() == 0){
            password_field.setBorder(Border.stroke(Color.CRIMSON));
            System.out.println("Введите пароль!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Введите пароль!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            password_field.setText("");
            password_field.setBorder(Border.stroke(Color.LIGHTGREY));
            return false;
        }
        return true;
    }
    @FXML
    void vhod(ActionEvent event) throws IOException {
        String login = login_field.getText().trim();
        String password = password_field.getText().trim();
        if (isDataCorrectt()) {
            if (login.equals("admin") && password.equals("123456")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuAdmin.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Main.setNewScene(scene);
            } else {
                boolean found = false;
                User blockedUser = null;
                for (User user : Main.users) {
                    if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                        found = true;
                        if (user.isBlocked()) {
                            blockedUser = user;
                            break;
                        }
                    }
                }
                if (found) {
                    System.out.println("Введенные данные найдены.");
                    if (blockedUser != null) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Внимание!");
                        alert.setHeaderText(null);
                        alert.setContentText("Ваш аккаунт заблокирован.");
                        String imagePath = "/image/27.png";
                        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                        Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(image);
                        alert.showAndWait();
                        login_field.setText("");
                        password_field.setText("");
                    } else {
                        Main.num = Main.users.size() - 1;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUser.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Main.mainStage.sizeToScene();
                        Main.mainStage.centerOnScreen();
                        Main.setNewScene(scene);
                    }
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Внимание!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Введенные данные не найдены в списке.");
                    String imagePath = "/image/27.png";
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                    Stage stage=(Stage) alert1.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(image);
                    alert1.showAndWait();
                    login_field.setText("");
                    password_field.setText("");
                }
            }
        }
    }
    @FXML
    void registration(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> login_field.requestFocus());
    }
}