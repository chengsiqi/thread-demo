package com.example.concurrent.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author csq
 * @date 2020/4/16 10:21
 * @description
 *
 * 生产者
 **/
public class Producer implements Runnable {
    private BlockingQueue<Task> buffer;

    public Producer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                Task task = new Task();
                // 调用put方法向队列中插入元素时, 如果队列已满, 它会让插入元素的线程等待队列腾出空间
                buffer.put(task);
                System.out.println("Producer[" +Thread.currentThread().getName() + "]put " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
