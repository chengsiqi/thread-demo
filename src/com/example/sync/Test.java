package com.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csq
 * @date 2020/4/14 11:12
 * @description
 *
 * 基于synchronized关键字的锁机制有一下问题：
 *  1). 锁只有一种类型, 而且对所有同步操作都是一样的作用
 *  2). 锁只能在代码块或者方法开始的地方获得, 在结束的地方释放
 *  3). 线程要么得到锁, 要么阻塞, 没有其他的可能性
 *
 * Java 5 对锁机制进行了重构, 提供了显示的锁, 这样可以在以下几个方面提升锁机制：
 *  1). 可以添加不同类型的锁, 例如读取锁和写入锁
 *  2). 可以在一个方法中加锁, 在另一个方法中解锁
 *  3). 可以使用tryLock方式尝试获取锁, 如果得不到锁可以等待, 回退, 或者干点别的事情,
 *      当然也可以在超时之后放弃操作
 *
 * 显示的锁都实现了java.util.concurrent.Lock接口, 主要有两个实现类：
 *  1). ReentrantLock：比synchronized稍微灵活一些的重入锁
 *  2). ReentrantReadWriteLock： 在读操作很多写操作很少时性能更好的一种重入锁
 *
 *
 * 请说出与线程同步相关的方法
 * 答：
 *  1). wait() 使一个线程处于等待(阻塞)状态,并且释放所持有的对象的锁
 *  2). sleep() 使一个正在运行的线程处于睡眠状态, 是一个静态方法, 调用此方法要捕捉InterruptedException异常
 *  3). notify() 唤醒一个处于等待状态的线程, 当然在调用此方法的时候, 并不能确切的唤醒某一个等待状态的线程,
 *      而是由JVM确定唤醒哪个线程, 而且与优先级无关
 *  4). notifyAll(). 唤醒所有处于等待状态的线程, 注意并不是给所有唤醒线程一个对象的锁, 而是让它们竞争
 *  5). JDK1.5通过Lock接口提供了显示的锁机制, 增强了灵活性以及线程的协调。Lock接口中定义了加锁(lock())和解锁(unlock())
 *      方法, 同时还提供了newCondition()方法来产生用于线程之间通信的Condition对象
 *  6). JDK1.5还提供了信号量(semaphore)机制, 信号量可以用来限制对某个共享资源进行访问的线程的数量
 *      在对资源进行访问之前, 线程必须得到信号量的许可(调用Semaphore对象的acquire()方法)；在完成对芋圆的访问后，
 *      线程必须向信号量归还许可(调用Semaphore对象的release()方法)
 *
 * 下面的例子演示了100个线程同时向一个银行账户中存入1元钱，在没有使用同步机制和使用同步机制情况下的执行情况。
 **/
public class Test {

    public static void main(String[] args){
        Account account = new Account();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= 100; i++){
            executorService.execute(new AddMoneyThread(account, 1));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()){}


        /**
         * 在没有同步的情况下，执行结果通常是显示账户余额在10元以下，出现这种状况的原因是，
         * 当一个线程A试图存入1元的时候，另外一个线程B也能够进入存款的方法中，线程B读取到的账户余额
         * 仍然是线程A存入1元钱之前的账户余额，因此也是在原来的余额0上面做了加1元的操作，同理线程C也会
         * 做类似的事情，所以最后100个线程执行结束时，本来期望账户余额为100元，但实际得到的通常在10元以下。
         * 解决这个问题的办法就是同步，当一个线程对银行账户存钱时，需要将此账户锁定，待其操作完成后才允许其他的线程进行操作
         *
         * 代码有如下几种调整方案：
         *  1).在银行账户类的存款(deposit)方法加上同步关键字synchronized
         *  2).在线程调用存款方法时对银行账户进行同步
         *  3).通过JDK1.5显示的锁机制, 为每个银行账户创建一个锁对象, 在存款操作进行加锁和解锁的操作
         */
        System.out.println("账户余额：" + account.getBalance());
    }
}
