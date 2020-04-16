package com.example.concurrent.producerconsumer.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author csq
 * @date 2020/4/16 10:32
 * @description
 *
 * 临界资源
 *  并发环境中有着固定数量的资源
 * 互斥
 *  对资源的访问是排他式的
 * 饥饿
 *  一个或一组线程长时间或永远无法取得进展
 * 死锁
 *  两个或多个线程相互等待对方结束
 * 活锁
 *  想要执行的线程总是发现其他的线程正在执行以至于长时间或永远无法执行
 *
 *
 * 生产者-消费者
 *  一个或多个生产者创建某些工作并将其置于缓冲区或队列中, 一个或多个消费者会从队列中获得这些工作并完成之
 *  这里的缓冲区或队列是临界资源。当缓冲区或队列放满的时候, 生产者会被阻塞；而缓冲区或队列为空的时候, 消费者会被阻塞
 *  生产者和消费者的调度是通过两者相互交换信号完成的
 **/
public class Test {

    public static void main(String[] args){
        BlockingQueue<Task> buffer = new LinkedBlockingQueue<>(Constants.MAX_BUFFER_SIZE);
        ExecutorService service = Executors.newFixedThreadPool(Constants.NUM_PRODUCER + Constants.NUM_CONSUMER);

        for (int i = 1; i <= Constants.NUM_PRODUCER; i++){
            service.execute(new Producer(buffer));
        }

        for (int i = 1; i <= Constants.NUM_CONSUMER; i++){
            service.execute(new Consumer(buffer));
        }
    }
}
