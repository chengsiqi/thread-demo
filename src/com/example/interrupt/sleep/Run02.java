package com.example.interrupt.sleep;

/**
 * @author csq
 * @date 2020/1/20 15:40
 * @description
 **/
public class Run02 {

    public static void main(String[] args){
        MyThread02 thread = new MyThread02();
        thread.start();
        thread.interrupt();
        System.out.println("end!");
    }
}
