package com.example.concurrent.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author csq
 * @date 2020/4/16 10:18
 * @description
 **/
public class Consumer implements Runnable {
    private BlockingQueue<Task> buffer;

    public Consumer(BlockingQueue<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                // 调用take方法从队列中取出元素时, 如果队列为空, 取出元素的线程就会阻塞
                Task take = buffer.take();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "]got " + take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
