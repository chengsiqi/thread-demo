package com.example;

/**
 * @author csq
 * @date 2020/1/20 16:14
 * @description
 **/
public class MyThread01 extends Thread {

    private int count = 5;

    /**
     * 通过在run方法上 加入synchronized关键字, 使多个线程在执行run方法时, 以排队的方式进行处理。
     * 当一个线程调用run前, 先判断run方法有没有被上锁, 如果上锁, 说明有其它线程正在调用run方法, 必须
     * 等其他线程对run方法调用结束后才可以执行run方法。这样也就实现了排队调用run方法的目的, 也就达到了按
     * 顺序对count变量减1的效果了。 synchronized可以在任意对象及方法上加锁, 而加锁的这段代码称为“互斥区”或“临界区”
     *
     * 非线程安全 主要是指多个线程对同一个对象中的同一个实例变量进行操作时会出现值被更改、值不同步的情况, 进而影响程序的执行流程。
     */
    @Override
    public synchronized void run(){
        super.run();
        count -- ;
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 此示例不要用for语句, 因为使用同步后其他线程就得不到运行的机会了, 一直由一个线程进行减法运算
        System.out.println("由 " + currentThread().getName() + " 计算，count= " + count );
    }

}
