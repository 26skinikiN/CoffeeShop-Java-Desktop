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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class qweController {

    @FXML
    private Button SotrDown1;

    @FXML
    private Button addIn1;

    @FXML
    private Button backToMenuUser1;

    @FXML
    private Button closeFilter1;

    @FXML
    private Button closeFilterbyletter1;

    @FXML
    private Button closeSort1;

    @FXML
    private Button closefilterbycost;

    @FXML
    private Button filterByCost1;

    @FXML
    private Button filterByLetter1;

    @FXML
    private TextField letterforfilter1;

    @FXML
    private TextField maxcost1;

    @FXML
    private TextField mincost1;

    @FXML
    private TableColumn<?, ?> nameColumn11;

    @FXML
    private TableColumn<?, ?> nameColumn21;

    @FXML
    private TextField nameproduct1;

    @FXML
    private Button sortUp1;

    @FXML
    private TableView<moto> tableOfProducts1;


    @FXML
    void SotrDown1(ActionEvent event) {
        RunnableSortedDown sortedDown = new RunnableSortedDown();
        Thread r = new Thread(sortedDown,"runnable");
        r.start();
        try {
            r.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tableOfProducts1.setItems(getObservableNecklaceList());
        tableOfProducts1.refresh();
    }


    public static ObservableList<moto> getObservableNecklaceList() {
        return FXCollections.observableList(Main.moto3);
    }

    @FXML
    void initialize(){
        Main.moto3 = Main.moto1;
        letterforfilter1.textProperty().addListener((observableValue, oldValue, newValue) ->{
            try {
                filter();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        mincost1.textProperty().addListener((observableValue, oldValue, newValue) ->{
            try {
                filter();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });
        maxcost1.textProperty().addListener((observableValue, oldValue, newValue) ->{
            try {
                filter();
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        });

        initTable();

    }
    public void filter() throws InterruptedException {
        List<moto> filteredList = new ArrayList<>(Main.moto1);

        // Фильтрация по букве
        String prefix = letterforfilter1.getText();
        filteredList = filteredList.stream()
                .filter(moto -> moto.getName().startsWith(prefix))
                .collect(Collectors.toList());

        // Фильтрация по минимальной цене
        String minPriceText = mincost1.getText();
        if (!minPriceText.isEmpty()) {
            try {
                int minPrice = Integer.parseInt(minPriceText);
                filteredList = filteredList.stream()
                        .filter(moto -> moto.getCost() >= minPrice)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат числа");
            }
        }

        // Фильтрация по максимальной цене
        String maxPriceText = maxcost1.getText();
        if (!maxPriceText.isEmpty()) {
            try {
                int maxPrice = Integer.parseInt(maxPriceText);
                filteredList = filteredList.stream()
                        .filter(moto -> moto.getCost() <= maxPrice)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный формат числа");
            }
        }

        Main.moto3 = new ArrayList<>(filteredList);
        initTable();
    }


    private void initTable() {
        // указываем тип данных столбца и связываем его с соответствующим полем в классе MyData
        TableColumn<moto, String> nameColumn = (TableColumn<moto, String>) nameColumn11;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<moto, Integer> costColumn = (TableColumn<moto, Integer>) nameColumn21;
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        // создаем список с данными
        ObservableList<moto> data = FXCollections.observableArrayList(Main.moto3);
        // заполняем таблицу данными
        tableOfProducts1.setItems(data);
    }


    @FXML
    void addIn1(ActionEvent event) {
        boolean productFound = false;

        for (moto i : Main.moto1) {
            if (nameproduct1.getText().equals(i.getName())) {
                Main.users.get(Main.num).buyUser.add(i);
                productFound = true;
                break;  // Товар найден, нет необходимости проверять дальше
            }
        }

        if (!productFound) {
            // Товар не найден, выводим сообщение об ошибке
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Товар с именем " + nameproduct1.getText() + " не найден!");
            String imagePath = "/image/27.png";
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
            Stage stage=(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image);
            alert.showAndWait();
            nameproduct1.setText("");
            return;  // Прерываем выполнение метода, так как товар не найден
        }

        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream(Main.filename2)))) {
            oos.writeObject(Main.users);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText(null);
        alert1.setContentText("Вы добавили " + nameproduct1.getText() + ".");
        String imagePath = "/image/27.png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        Stage stage=(Stage) alert1.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image);
        alert1.showAndWait();
        nameproduct1.setText("");
    }

    @FXML
    void backToMenuUser1(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Main.setNewScene(scene);
    }

    @FXML
    void sortUp1(ActionEvent event) {
        ThreadSortedUp sortedUp = new ThreadSortedUp(Main.moto3);
        Thread thread = new Thread(sortedUp);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tableOfProducts1.setItems(getObservableNecklaceList());
        tableOfProducts1.refresh();
    }

}
