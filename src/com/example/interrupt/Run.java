package com.example.interrupt;

/**
 * @author csq
 * @date 2020/1/21 10:51
 * @description
 *
 * 判断线程的状态是不是停止的 Thread提供了两种方法
 *  1、this.interrupted(): 测试当前线程是否已经是中断状态, 执行后具有将状态标志清除为false的功能
 *  2、this.isInterrupted(): 测试线程是否已经是中断状态, 但不清楚状态标志
 **/
public class Run {

    public static void main(String[] args){
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();

            System.out.println("是否停止1？ = " + Thread.interrupted());
            System.out.println("是否停止2？ = " + Thread.interrupted());
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
    }
}
