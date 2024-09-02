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

public class AdminProductsController {
    @FXML
    private TextField addcost;
    @FXML
    private TextField productname;
    @FXML
    private TextField addname;
    @FXML
    private TableColumn<?, ?> col1;

    @FXML
    private TableColumn<?, ?> col2;

    @FXML
    private Button addnewproduct;

    @FXML
    private Button backToMenuAdmin;

    @FXML
    private TextField changecost;

    @FXML
    private Button changecostt;

    @FXML
    private TextField changename;

    @FXML
    private Button changenamee;

    @FXML
    private Button deleteproduct;

    @FXML
    private TextField namefordelete;

    @FXML
    private TableView<moto> tableOfProducts;
    static  String productsFileName = "text.dat";
    public static ObservableList<moto> getObservableStonesList() {
        return FXCollections.observableList(Main.moto1);
    }

    @FXML
    void addnewproduct(ActionEvent event) {
        try {
            String name = addname.getText().trim();
            int cost = Integer.parseInt(addcost.getText().trim());

            Main.moto1.add(new moto(name, cost));

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(productsFileName))) {
                oos.writeObject(Main.moto1);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            tableOfProducts.setItems(getObservableStonesList());
            tableOfProducts.refresh();
            addname.setText("");
            addcost.setText("");
        } catch (NumberFormatException e) {
            // Обработка ошибки в случае ввода текста вместо числа
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Ошибка ввода");
//            alert.setHeaderText(null);
//
//            alert.setContentText("Пожалуйста, введите число в поле стоимости.");
//            alert.showAndWait();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, введите число в поле стоимости.");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
        }
    }

    @FXML
    void initialize(){
        initTable();
    }
    private void initTable() {
        TableColumn<moto, String> nameColumn = (TableColumn<moto, String>) col1;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<moto, Integer> costColumn = (TableColumn<moto, Integer>) col2;
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        // создаем список с данными
        ObservableList<moto> data = FXCollections.observableArrayList(Main.moto1);
        // заполняем таблицу данными
        tableOfProducts.setItems(data);
    }

    @FXML
    void backToMenuAdmin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @FXML
    void changecost(ActionEvent event) {
        String nameprod = productname.getText().trim();
        try {
            for (moto i : Main.moto1) {
                if (nameprod.equals(i.getName())) {
                    if (changecost.getLength() != 0) {
                        int changecostproduct = Integer.parseInt(changecost.getText().trim());
                        i.setCost(changecostproduct);
                    }
                }
            }

            tableOfProducts.setItems(getObservableStonesList());
            tableOfProducts.refresh();
            productname.setText("");
            changecost.setText("");
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(productsFileName))) {
                oos.writeObject(Main.moto1);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (NumberFormatException e) {
            // Обработка ошибки в случае ввода текста вместо числа
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, введите число в поле стоимости.");
            alert.showAndWait();
        }
    }

    @FXML
    void changename(ActionEvent event) {
        String nameprod = productname.getText().trim();
        for (moto i : Main.moto1){
            if (nameprod.equals(i.getName())){
                if (changename.getLength()!=0){
                    String changenameproduct = changename.getText().trim();
                    i.setName(changenameproduct);
                }
            }
        }
        tableOfProducts.setItems(getObservableStonesList());
        tableOfProducts.refresh();
        productname.setText("");
        changename.setText("");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(productsFileName))) {
            oos.writeObject(Main.moto1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean isNameUnique(String name) {
        for (moto i : Main.moto1) {
            if (i.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void deleteproduct(ActionEvent event) {
        if(namefordelete.getLength() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Введите название продукта!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            namefordelete.setText("");
            return;
        }
        if (isNameUnique(namefordelete.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Внимание!");
            alert.setHeaderText(null);
            alert.setContentText("Такого продукта нет!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            namefordelete.setText("");
            return;
        }
        for (moto i : Main.moto1) {
            if (namefordelete.getText().equals(i.getName())) {
                Main.moto1.remove(i);
                break;
            }
        }
        namefordelete.setText("");
        tableOfProducts.setItems(getObservableStonesList());
        tableOfProducts.refresh();
        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(productsFileName)))){
            oos.writeObject(Main.moto1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
