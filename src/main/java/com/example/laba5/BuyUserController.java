package com.example.laba5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class BuyUserController {
    @FXML
    private Button backToMenuUser;

    @FXML
    private TableView<moto> tableOfBuyUser;

    @FXML
    private TableColumn<?, ?> colomn1;

    @FXML
    private TableColumn<?, ?> colomn2;

    @FXML
    private Label totCoste;
    @FXML
    void initialize(){
        setTotalCost();
        initTable();

    }
    private void initTable() {
        TableColumn<moto, String> nameColumn = (TableColumn<moto, String>) colomn1;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<moto, Integer> costColumn = (TableColumn<moto, Integer>) colomn2;
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        // создаем список с данными
        ObservableList<moto> data = FXCollections.observableArrayList(Main.users.get(Main.num).buyUser);
        // заполняем таблицу данными
        tableOfBuyUser.setItems(data);
    }

    @FXML
    void backToMenuUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }
    private void setTotalCost() {
        int totalCost = 0;

        for (moto moto : Main.users.get(Main.num).buyUser ) {
            totalCost += moto.getCost();
        }

        totCoste.setText(String.valueOf(totalCost));
    }

}
