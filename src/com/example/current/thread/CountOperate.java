package com.example.current.thread;

/**
 * @author csq
 * @date 2020/1/20 17:44
 * @description
 **/
public class CountOperate extends Thread {

    public CountOperate(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName()=" + currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()=" + currentThread().getName());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("run---end");
    }
}
