package com.example.sleep;

/**
 * @author csq
 * @date 2020/1/21 9:51
 * @description
 *
 * 方法sleep()的作用是在指定的毫秒数内让当前“正在执行的线程”休眠(暂停执行)
 * 这个“正在执行的线程”是指this.currentThread()返回的线程
 **/
public class MyThread1 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("run threadName=" + currentThread().getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName=" + currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
