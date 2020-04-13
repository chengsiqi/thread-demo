package com.example;

/**
 * @author csq
 * @date 2020/1/20 15:49
 * @description
 **/
public class MyThread extends Thread {

    private int count = 5;

    public MyThread(String name){
        super();
        // 设置线程名称
        this.setName(name);
    }

    @Override
    public void run(){
        super.run();
        while(count  > 0){
            count -- ;
            System.out.println("由" + currentThread().getName()+ " 计算，count= " + count);
        }
    }
}
