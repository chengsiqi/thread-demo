package com.example.concurrent.collection;

import java.util.ArrayList;

/**
 * @author csq
 * @date 2020/4/14 16:59
 * @description
 **/
public class MyThread implements Runnable {

    private ArrayList arrayList;

    public MyThread(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++){
            arrayList.add(i);
        }
    }
}
