package com.example.interrupt;

/**
 * @author csq
 * @date 2020/1/21 11:04
 * @description
 **/
public class Run2 {

    public static void main(String[] args){
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？ =" + Thread.interrupted());
        // interrupted()方法具有清除状态的功能, 所以第二次调用interrupted()方法返回的值是false
        System.out.println("是否停止2？ =" + Thread.interrupted());
        System.out.println("end!");
    }
}
