package com.example.controller;

/**
 * @author csq
 * @date 2020/1/20 16:46
 * @description
 *
 * 本类模拟成一个Servlet组件
 **/
public class LoginServlet {

    private static String usernameRef;
    private static String passwordRef;

    public synchronized static void doPost(String username, String password){
        try {
            usernameRef = username;
            if("a".equals(username)){
                Thread.sleep(1000);
            }
            passwordRef = password;
            System.out.println("username=" + usernameRef + " password=" + passwordRef);
            // System.out.println("username=" + username + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
