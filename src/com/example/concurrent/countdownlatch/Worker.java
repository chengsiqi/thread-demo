package com.example.concurrent.countdownlatch;

/**
 * @author csq
 * @date 2020/4/14 16:33
 * @description
 *
 * 工人类
 **/
public class Worker {
    // 名字
    private String name;
    // 工作持续时间
    private long workDuration;

    public Worker(String name, long workDuration) {
        this.name = name;
        this.workDuration = workDuration;
    }

    public void doWork(){
        System.out.println(name + " begins to work...");
        try {
            // 模拟工作执行时间
            Thread.sleep(workDuration);
            System.out.println(name + " work duration " + workDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " has finished the job...");
    }
}
