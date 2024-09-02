package com.example.laba5;

import java.io.*;
import java.util.ArrayList;

public class File {
    public static void readfile(String filename2){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename2))){
            Main.users = (ArrayList<User>) ois.readObject();
        }
        catch (FileNotFoundException ex){
            System.out.println("Файл не найден!!!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        catch (IOException ex){
            System.out.println("Ошибка чтения!!!");
        }
    }
    public static void readMy(String filename1){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename1))){
            Main.moto1 = (ArrayList<moto>) ois.readObject();
        }
        catch (FileNotFoundException ex){
            System.out.println("Файл не найден!!!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Класс не найден");
        }
        catch (IOException ex){
            System.out.println("Ошибка чтения!!!");
        }
    }

}
