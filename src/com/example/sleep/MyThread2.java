package com.example.sleep;

/**
 * @author csq
 * @date 2020/1/21 10:13
 * @description
 **/
public class MyThread2 extends Thread {

    @Override
    public void run(){
        try {
            System.out.println("run threadName=" + currentThread().getName() +
                    " begin =" +System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("run threadName=" + currentThread().getName() +
                    " end =" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
