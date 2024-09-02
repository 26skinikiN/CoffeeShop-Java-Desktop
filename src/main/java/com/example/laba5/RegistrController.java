package com.example.laba5;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class RegistrController {
    @FXML
    private Button backToMenuAuth;
    @FXML
    private TextField login_fieldreg;

    @FXML
    private PasswordField password_fieldreg;

    @FXML
    private Button registSingUpButton;

    public boolean isLoginUnique(String login) {
        for (User i : Main.users) {
            if (i.getUsername().equals(login)) {
                return false;
            }
        }
        return true;
    }
    static  String usersFileName = "users.dat";

    private boolean isDataCorrect() {
        if (!isLoginUnique(login_fieldreg.getText().trim())) {
            login_fieldreg.setBorder(Border.stroke(Color.CRIMSON));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь с таким логином уже существует!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            login_fieldreg.setText("");
            password_fieldreg.setText("");
            login_fieldreg.setBorder(Border.stroke(Color.LIGHTGREY));
            return false;
        }
        return true;
    }

    @FXML
    void registrationuser(ActionEvent event) throws IOException {
        String login = login_fieldreg.getText().trim();
        String password = password_fieldreg.getText().trim();
        if (isDataCorrect()) {
            boolean blocked = false;
            Main.users.add(new User(login, password, blocked));
            System.out.println(Main.users);
            try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(usersFileName)))) {
                oos.writeObject(Main.users);
                Main.num = Main.users.size() - 1;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUser.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Main.setNewScene(scene);
        }
    }

//    }
    @FXML
    void backToMenuAuth(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("authorisation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }
}
