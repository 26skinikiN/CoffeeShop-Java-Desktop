package com.example.laba5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ThreadSortedUp extends Thread{
    public ThreadSortedUp(ArrayList<moto> moto3) {
    }

    @Override
    public  void run() {
        Main.moto3=Main.moto3.stream().sorted(Comparator.comparing(moto::getCost)).collect(Collectors.toCollection(ArrayList::new));
    }
}
