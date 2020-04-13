package com.example.extthread;

/**
 * @author csq
 * @date 2020/1/20 16:52
 * @description
 **/
public class Run {

    public static void main(String[] args){
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}
