package com.example.laba5;

import java.io.Serializable;
import java.util.ArrayList;

public class moto implements Serializable {
    private int cost;
    private String name;

    public moto(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
    public static void view(ArrayList<moto> moto) {
        int k = 1;
        for (moto i : moto) {
            System.out.println(k +"\tНазвание:" + i.name + "\tСтоимость: " + i.cost);
            k += 1;
        }
    }
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost=cost;
    }
    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

}
