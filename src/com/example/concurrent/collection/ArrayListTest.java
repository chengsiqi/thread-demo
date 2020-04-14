package com.example.concurrent.collection;

import java.util.ArrayList;

/**
 * @author csq
 * @date 2020/4/14 16:57
 * @description
 **/
public class ArrayListTest {

    private static ArrayList arrayList = new ArrayList();

    public static void main(String [] args) throws InterruptedException {
        Thread t1 = new Thread(new MyThread(arrayList));
        Thread t2 = new Thread(new MyThread(arrayList));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(arrayList);
    }
}
