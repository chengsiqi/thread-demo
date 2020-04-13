package com.example.extthread1;

/**
 * @author csq
 * @date 2020/1/20 17:01
 * @description
 *
 * 留意 i-- 与 System.out.println()的异常
 **/
public class MyThread extends Thread {

    private int i = 5;

    @Override
    public synchronized void run(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 虽然println()方法在内部是同步的, 但i--的操作却是在进入println()之前发生的, 所以有发生非线程安全问题的概率
        // 所以, 为了防止发生非线程安全问题, 还是应继续使用同步方法
        System.out.println("i=" + (i--) + " threadName=" + currentThread().getName());
        // 注意：代码i--由前面项目中单独一行运行改成在当前项目中, 在println()方法中直接进行打印
    }
}
