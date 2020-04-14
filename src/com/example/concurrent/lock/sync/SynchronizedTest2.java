package com.example.concurrent.lock.sync;

/**
 * @author csq
 * @date 2020/4/14 15:33
 * @description
 *
 *
 **/
public class SynchronizedTest2 {

    /**
     * 3. 一个线程获取了该对象的锁之后, 其它线程来访问其它非Synchronized实例方法现象
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
        SynchronizedTest2 test = new SynchronizedTest2();

        // ::
        new Thread(test::method1).start();
        new Thread(test::method2).start();

        // 分析：当线程1还在执行时, 线程2也执行了, 所以当其他线程来访问非synchronized修饰的方法时时可以访问的
    }
}
