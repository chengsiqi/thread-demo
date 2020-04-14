package com.example.concurrent.lock.sync;

/**
 * @author csq
 * @date 2020/4/14 15:33
 * @description
 *
 *
 **/
public class SynchronizedTest1 {

    /**
     * 2. 一个线程获取了该对象的锁之后, 其它线程来访问其它Synchronized实例方法现象
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

    public synchronized void method2(){
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
        SynchronizedTest1 test = new SynchronizedTest1();

        // ::
        new Thread(test::method1).start();
        new Thread(test::method2).start();

        // 分析：可以看出其他线程来访问synchronized修饰的其他方法时需要等待线程1先把锁释放
    }
}
