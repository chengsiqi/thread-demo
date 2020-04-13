package com.example.interrupt;

/**
 * @author csq
 * @date 2020/1/21 10:30
 * @description
 *
 * 停止一个线程可以使用Thread.stop()方法, 但最好不要使用它。 这个方法是不安全的(unsafe), 而且是已被弃用作废的(deprecated)
 *
 * 调用interrupt() 方法来停止线程
 * 调用interrupt()方法仅仅是在当前线程中打了一个停止的标记, 并不是真的停止线程
 **/
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        for(int i = 0; i < 500000; i++){
            System.out.println("i=" + (i + 1));
        }
    }
}
