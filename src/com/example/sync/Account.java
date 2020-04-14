package com.example.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author csq
 * @date 2020/4/14 10:37
 * @description
 *
 * 银行账户类
 **/
public class Account {
    // 账户余额
    private double balance;

    // 锁对象
    // private final Lock accountLock = new ReentrantLock();

    /**
     * 存款
     * @param money 存入金额
     */
    public /*synchronized*/ void deposit(double money){
        // 加锁
        // accountLock.lock();
        try {
            double newBalance = balance + money;
            try {
                // 模拟此业务需要一段时间
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
        } finally {
            // 解锁的方法调用最好能够在finally块中, 因为这里是释放外部资源最好的地方
            // 当然也是释放锁的最佳位置, 因为不管正常异常可能都要释放掉锁来给其他线程以运行的机会
            // 解锁
            // accountLock.unlock();
        }
    }

    /**
     * 获取账户余额
     */
    public double getBalance(){
        return balance;
    }
}
