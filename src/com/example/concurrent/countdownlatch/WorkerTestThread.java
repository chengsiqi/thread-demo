package com.example.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author csq
 * @date 2020/4/14 16:36
 * @description
 *
 * 工人做工作
 **/
public class WorkerTestThread implements Runnable {

    private Worker worker;
    private CountDownLatch countDownLatch;

    public WorkerTestThread(Worker worker, CountDownLatch countDownLatch) {
        this.worker = worker;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        // 让工人开始工作
        worker.doWork();
        // 工作完成后倒计时次数减一
        countDownLatch.countDown();
    }
}
