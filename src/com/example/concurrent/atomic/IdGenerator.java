package com.example.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author csq
 * @date 2020/4/13 10:10
 * @description
 *
 * Java 5中的java.util.concurrent包下面有一个atomic子包，其中有几个以Atomic打头的类，
 * 例如AtomicInteger和AtomicLong。它们利用了现代处理器的特性，可以用非阻塞的方式完成原子操作
 *
 * ID 序列生成器
 **/
public class IdGenerator {

    private final AtomicLong sequenceNumber = new AtomicLong(0);

    public long next(){
        return sequenceNumber.getAndIncrement();
    }

    private Long number = new Long(0);

    public long next1() {
        number = number + 1;
        return number;
    }

    @Test
    public void test(){
        //IdGenerator idGenerator = new IdGenerator();
        for (int i = 0; i < 50; i++){
            System.out.println(next());
        }
    }

    public static void main(String[] args){
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        Thread t3 = new Thread(myThread);
        Thread t4 = new Thread(myThread);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
