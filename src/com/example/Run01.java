package com.example;

/**
 * @author csq
 * @date 2020/1/20 16:18
 * @description
 *
 * 共享数据的情况就是多个线程可以访问同一个变量, 比如在实现投票功能的软件时, 多个线程可以同时处理同一个人的票数
 **/
public class Run01 {

    public static void main(String[] args){
        MyThread01 thread = new MyThread01();
        Thread a = new Thread(thread, "A");
        Thread b = new Thread(thread, "B");
        Thread c = new Thread(thread, "C");
        Thread d = new Thread(thread, "D");
        Thread e = new Thread(thread, "E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }
}
