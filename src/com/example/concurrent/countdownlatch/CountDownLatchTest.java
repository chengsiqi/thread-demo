package com.example.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author csq
 * @date 2020/4/14 16:39
 * @description
 *
 * CountDownLatch是一种简单的同步模式, 它让一个线程可以等待一个或多个线程完成它们的工作
 * 从而避免对临界资源并发访问所引发各种问题
 **/
public class CountDownLatchTest {

    // 最大工作时间
    private static final int MAX_WORK_DURATION = 5000;
    // 最小工作时间
    private static final int MIN_WORK_DURATION = 1000;

    // 产生随机的工作时间
    public static long getRandomWorkDuration(long min, long max){
        return (long) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args){
        // 创建倒计时闩并指定倒计时次数为2
        CountDownLatch latch = new CountDownLatch(2);
        Worker w1 = new Worker("骆昊", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));
        Worker w2 = new Worker("王大锤", getRandomWorkDuration(MIN_WORK_DURATION, MAX_WORK_DURATION));

        new Thread(new WorkerTestThread(w1, latch)).start();
        new Thread(new WorkerTestThread(w2, latch)).start();

        try {
            // 等待倒计时闩减到0
            latch.await();
            System.out.println("All jobs have been finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
