package com.example.isalive;

/**
 * @author csq
 * @date 2020/1/21 9:36
 * @description
 **/
public class CountOperate extends Thread {

    public CountOperate(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName()=" + currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()=" + currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("CountOperate---end");
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName()=" + currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()=" + currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("run---end");
    }
}
