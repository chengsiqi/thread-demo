package com.example.extthread;

import com.example.controller.LoginServlet;

/**
 * @author csq
 * @date 2020/1/20 16:51
 * @description
 **/
public class BLogin extends Thread {

    @Override
    public void run(){
        LoginServlet.doPost("b", "bb");
    }
}
