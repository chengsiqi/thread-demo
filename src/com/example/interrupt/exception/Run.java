package com.example.interrupt.exception;

/**
 * @author csq
 * @date 2020/1/22 9:23
 * @description
 **/
public class Run {

    public static void main(String[] args){
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(10);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}
