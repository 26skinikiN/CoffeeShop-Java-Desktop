package com.example.laba5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class RunnableSortedDown implements Runnable{
    public RunnableSortedDown() {
    }

    public void run() {
        Main.moto3=Main.moto3.stream().sorted(Comparator.comparing(moto::getCost).reversed()).collect(Collectors.toCollection(ArrayList::new));
    }
}
