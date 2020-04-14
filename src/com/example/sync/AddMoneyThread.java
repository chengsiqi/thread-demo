package com.example.sync;

/**
 * @author csq
 * @date 2020/4/14 10:44
 * @description
 *
 * 存钱线程类
 **/
public class AddMoneyThread implements Runnable {

    // 存钱账户
    private Account account;
    // 存入金额
    private double money;

    public AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public void run() {
        synchronized (account) {
            account.deposit(money);
        }
    }
}
