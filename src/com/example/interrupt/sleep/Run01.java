package com.example.interrupt.sleep;

/**
 * @author csq
 * @date 2020/1/20 15:40
 * @description
 **/
public class Run01 {

    public static void main(String[] args){
        try {
            MyThread01 thread = new MyThread01();
            thread.start();
            Thread.sleep(200);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
