package com.example.concurrent.lock.sync;

/**
 * @author csq
 * @date 2020/4/14 15:10
 * @description
 *
 **/
public class SynchronizedTest3 {
    /**
     * 4. 当多个线程作用于不同的对象
     */
    public synchronized void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public void method2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String [] args){
        SynchronizedTest3 test1 = new SynchronizedTest3();
        SynchronizedTest3 test2 = new SynchronizedTest3();

        // ::
        new Thread(test1::method1).start();
        new Thread(test2::method2).start();

        // 分析：因为两个线程作用于不同的对象,获得的是不同的锁, 所以互相不影响
    }
}
