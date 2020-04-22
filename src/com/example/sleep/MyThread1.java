package com.example.sleep;

/**
 * @author csq
 * @date 2020/1/21 9:51
 * @description
 *
 * 方法sleep()的作用是在指定的毫秒数内让当前“正在执行的线程”休眠(暂停执行)
 * 这个“正在执行的线程”是指this.currentThread()返回的线程
 *
 * sleep()方法（休眠）是线程类（Thread）的静态方法，调用此方法会让当前线程
 * 暂停执行指定的时间，将执行机会（CPU）让给其他线程，但是对象的锁依然保持，
 * 因此休眠时间结束后会自动恢复（线程回到就绪状态）。
 *
 * sleep()方法给其他线程运行机会时不考虑线程的优先级, 因此会给低优先级的线程以运行的机会；
 * yield()方法只会给相同优先级或更高优先级的线程以运行的机会
 *
 * 线程执行sleep()方法后转入阻塞(blocked)状态, 而执行yield()方法后转入就绪(ready)状态
 *
 * sleep()方法声明抛出InterruptedException, 而yield()方法没有声明任何异常
 *
 * sleep()方法比yield()方法(跟操作系统CPU调度相关)具有更好的可移植性
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
