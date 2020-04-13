package com.example.sleep;

/**
 * @author csq
 * @date 2020/1/21 10:04
 * @description
 **/
public class Run1 {

    public static void main(String[] args){
        MyThread1 myThread = new MyThread1();
        System.out.println("begin=" + System.currentTimeMillis());
        myThread.run();
        System.out.println("end=" + System.currentTimeMillis());
    }
}
