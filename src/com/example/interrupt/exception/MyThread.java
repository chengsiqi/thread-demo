package com.example.interrupt.exception;

/**
 * @author csq
 * @date 2020/1/22 9:20
 * @description
 **/
public class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            for(int i = 0; i < 50000; i++){
                if(interrupted()){
                    System.out.println("已经是停止状态了！我要退出了！");
                    // break;
                    throw new InterruptedException();
                }
                System.out.println("i=" + (i + 1));
            }
            // System.out.println("我被输出, 如果此代码是for又继续执行, 线程并未停止");
            System.out.println("我在for下面");
        } catch (InterruptedException e) {
            System.out.println("进Mythread.java类run方法中的catch了！");
            e.printStackTrace();
        }
    }
}
