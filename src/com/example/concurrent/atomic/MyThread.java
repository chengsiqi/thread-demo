package com.example.concurrent.atomic;

/**
 * @author csq
 * @date 2020/4/13 17:44
 * @description
 **/
public class MyThread extends  Thread{

    private IdGenerator idGenerator = new IdGenerator();

    @Override
    public void run() {
        // System.out.println(idGenerator.next());
        System.out.println(idGenerator.next1());
    }
}
