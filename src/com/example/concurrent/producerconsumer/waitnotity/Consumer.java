package com.example.concurrent.producerconsumer.waitnotity;

import java.util.List;

/**
 * @author csq
 * @date 2020/4/16 9:49
 * @description
 *
 * 消费者
 **/
public class Consumer implements Runnable {
    private List<Task> buffer;

    public Consumer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            synchronized (buffer) {
                while (buffer.isEmpty()){
                    try {
                        // wait() 使一个线程处于等待(阻塞)状态,并且释放所持有的对象的锁
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = buffer.remove(0);
                // notifyAll(). 唤醒所有处于等待状态的线程, 注意并不是给所有唤醒线程一个对象的锁, 而是让它们竞争
                buffer.notifyAll();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "]got " + task);
            }
        }
    }
}
