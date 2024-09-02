package com.example.laba5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class AdminFunctionController {
    @FXML
    private Button backToMenuAdmin;
    static String usersFileName = "users.dat";

    @FXML
    private Button block;

    @FXML
    private Button changelogin;

    @FXML
    private Button deleteUser;

    @FXML
    private TableView<User> tableOfUsers;

    @FXML
    private TableColumn<?, ?> column1;

    @FXML
    private TableColumn<?, ?> column2;
    @FXML
    private TextField namefodel;
    @FXML
    private TextField loginUser;
    @FXML
    private TextField loginUser1;

    @FXML
    private TextField loginUserchange;
    @FXML
    void initialize(){
        initTable();
    }
    private void initTable() {
        TableColumn<User, String> nameColumn = (TableColumn<User, String>) column2;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, Boolean> costColumn = (TableColumn<User, Boolean>) column1;
        costColumn.setCellValueFactory(new PropertyValueFactory<>("blocked"));
        // создаем список с данными
        ObservableList<User> data = FXCollections.observableArrayList(Main.users);
        // заполняем таблицу данными
        tableOfUsers.setItems(data);
    }

    @FXML
    void backToMenuAdmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @FXML
    void block(ActionEvent event) {
        boolean bl = true;
        String inlogin = loginUser1.getText().trim();

        boolean userFound = false;

        for(User i : Main.users){
            if(inlogin.equals(i.getUsername())){
                userFound = true;

                if(bl == i.getBlocked()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Внимание!");
                    alert.setHeaderText(null);
                    alert.setContentText("Данный пользователь уже заблокирован!");
                    String imagePath = "/image/27.png";
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                    Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(image);
                    alert.showAndWait();
                    loginUser1.setText("");
                    break;
                } else {
                    System.out.println(i.getUsername());
                    i.setBlocked(true);
                    tableOfUsers.setItems(getObservableList());
                    tableOfUsers.refresh();
                    loginUser1.setText("");

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(usersFileName))) {
                        oos.writeObject(Main.users);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        if (!userFound) {
            // Пользователь не найден, показываем сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь с таким логином не найден!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            loginUser1.setText("");
        }
    }

    @FXML
    void unblock(ActionEvent event) {
        boolean bl = false;
        String inlogin = loginUser1.getText().trim();
        boolean userFound = false;

        for(User i : Main.users){
            if(inlogin.equals(i.getUsername())){
                userFound = true;
                if(bl == i.getBlocked()){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Внимание!");
                    alert.setHeaderText(null);
                    alert.setContentText("Данный пользователь уже разблокирован!");
                    String imagePath = "/image/27.png";
                    Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                    Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(image);
                    alert.showAndWait();
                    loginUser1.setText("");
                    break;
                } else {
                    System.out.println(i.getUsername());
                    i.setBlocked(false);
                    tableOfUsers.setItems(getObservableList());
                    tableOfUsers.refresh();
                    loginUser1.setText("");

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(usersFileName))) {
                        oos.writeObject(Main.users);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }

        if (!userFound) {
            // Пользователь не найден, показываем сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь с таким логином не найден!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            loginUser1.setText("");
        }
    }

    @FXML
    void changelogin(ActionEvent event) {
        String namelog = loginUser.getText().trim();
        for (User i : Main.users){
            if (namelog.equals(i.getUsername())){
                if (loginUser.getLength()!=0){
                    String changenameuser = loginUserchange.getText().trim();
                    i.setUsername(changenameuser);
                }
            }
        }
        tableOfUsers.setItems(getObservableList());
        tableOfUsers.refresh();
        loginUser.setText("");
        loginUserchange.setText("");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(usersFileName))) {
            oos.writeObject(Main.users);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ObservableList<User> getObservableList() {
        return FXCollections.observableList(Main.users);
    }
    public boolean isLoginUnique(String login) {
        for (User i : Main.users) {
            if (i.getUsername().equals(login)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void deleteuser(ActionEvent event) {
        if (namefodel.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Введите логин пользователя!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            return;
        }
        if (isLoginUnique(namefodel.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Такого пользователя нет!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            namefodel.setText("");
            return;
        }
        for(User i : Main.users){
            if (namefodel.getText().equals(i.getUsername())){
                Main.users.remove(i);
                break;
            }
        }
        tableOfUsers.setItems(getObservableList());
        tableOfUsers.refresh();
        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(usersFileName)))){
            oos.writeObject(Main.users);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText("Вы удалили "+namefodel.getText()+".");
        String imagePath = "/image/27.png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        Stage stage=(Stage) alert1.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image);
        alert1.showAndWait();
        namefodel.setText("");
    }
}
