package com.example.sleep;

/**
 * @author csq
 * @date 2020/1/21 10:04
 * @description
 **/
public class Run2 {

    public static void main(String[] args){
        MyThread2 myThread = new MyThread2();
        System.out.println("begin=" + System.currentTimeMillis());
        myThread.start();
        System.out.println("end=" + System.currentTimeMillis());


        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " " + thread.getId());
    }
}
