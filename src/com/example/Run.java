package com.example;

/**
 * @author csq
 * @date 2020/1/20 16:06
 * @description
 *
 * 自定义线程类中的实例变量针对其他线程可以有共享与不共享之分,
 * 这在多个线程之间进行交互时是很重要的一个技术点。
 *
 * 本例 不共享数据的情况
 *
 * 一共创建了3个线程,每个线程都有各自的count变量, 自己减少自己的count变量的值
 * 这样的情况就是变量不共享, 此示例并不存在多个线程访问同一个实例变量的情况
 **/
public class Run {

    public static void main(String[] args){
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("B");
        MyThread c = new MyThread("C");
        a.start();
        b.start();
        c.start();
    }
}
