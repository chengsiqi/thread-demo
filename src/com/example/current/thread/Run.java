package com.example.current.thread;

/**
 * @author csq
 * @date 2020/1/21 9:11
 * @description
 **/
public class Run {

    public static void main(String[] args){
        CountOperate c = new CountOperate();
        /*Thread t = new Thread(c);
        t.setName("A");
        t.start();*/
        c.setName("A");
        c.start();
    }
}
