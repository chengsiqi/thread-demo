package com.example.concurrent.future;

import java.util.concurrent.*;

/**
 * @author csq
 * @date 2020/4/21 10:37
 * @description
 *
 * future.get方法阻塞问题的解决，实现按照任务完成的先后顺序获取任务的结果
 *
 * Future
 *  Future模式是多线程设计常用的一种设计模式, Future模式可以理解成：我有一个任务, 提交给了Future,
 *  Future替我完成这个任务, 期间我自己可以去做任何想做的事情, 一段时间之后, 我就便可以从Future那里取出结果
 *
 * Future提供了三种功能
 *  1、判断任务是否完成
 *  2、能够中断任务
 *  3、能够获取任务执行结果
 *
 * 向线程池中提交任务的submit方法不是阻塞方法，
 * 而Future.get方法是一个阻塞方法，当submit提交多个任务时，只有所有任务都完成后，才能使用get按照任务的提交顺序得到返回结果，
 * 所以一般需要使用future.isDone先判断任务是否全部执行完成，完成后再使用future.get得到结果。
 * （也可以用get (long timeout, TimeUnit unit)方法可以设置超时时间，防止无限时间的等待）
 *
 * 三段式的编程：
 *  1、启动多线程任务
 *  2、处理其他事情
 *  3、收集多线程任务结果
 *
 * Future虽然可以实现获取异步执行结果的需求, 但是它没有提供通知的机制,
 * 要么使用阻塞, 在future.get()的地方等待future返回的结果, 这时又变成同步操作;
 * 要么使用isDone()轮询的判断Future是否完成, 这样会耗费CPU的资源
 *
 * 解决Future的get()阻塞的方法： CompletionService和CompletableFuture(按照任务完成的先后顺序获取任务的结果)
 *
 * 1、 CompletionService是Java1.8之前最好用的方法
 *  能够实现按照任务完成的先后顺序获取任务的结果
 **/
public class CompletionServiceTest {

    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";

    public static void main(String[] args) throws Exception {
        // 创建一个线程池
        // 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
        // 当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(new MyThread1(commandstr01));
        completionService.submit(new MyThread2(commandstr02));

        executorService.shutdown();

        // CompletionService 的take()方法获取最先执行完的线程的Future对象。
        // CompletionService方法可以通过completionService.take().get()方法获取最快完成的线程的返回结果
        // (若当前没有线程执行完成则阻塞知道最快的线程执行结束)
        System.out.println(completionService.take().get());
        // 第二次调用则返回第二快完成的线程的返回结果
        System.out.println(completionService.take().get());
    }
}

class MyThread1 implements Callable<String> {
    // 要运行的命令
    private String commandStr;

    public MyThread1(String commandStr) {
        this.commandStr = commandStr;
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++){
            Thread.sleep(200);
            sum += i;
            System.out.println("MyThread1: " + i);
        }
        return String.valueOf(sum+300000);
    }
}

class MyThread2 implements Callable<String> {
    // 要运行的命令
    private String commandStr;

    public MyThread2(String commandStr) {
        this.commandStr = commandStr;
    }

    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 50; i++){
            Thread.sleep(200);
            sum += i;
            System.out.println("MyThread2: " + i);
        }
        return String.valueOf(sum+400000);
    }
}