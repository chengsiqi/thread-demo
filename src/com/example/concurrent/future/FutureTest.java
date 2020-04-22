package com.example.concurrent.future;

import java.util.concurrent.*;

/**
 * @author csq
 * @date 2020/4/21 10:37
 * @description
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
 * Future虽然可以实现获取异步执行结果的需求, 但是它没有提供通知的机制,
 * 要么使用阻塞, 在future.get()的地方等待future返回的结果, 这时又变成同步操作;
 * 要么使用isDone()轮询的判断Future是否完成, 这样会耗费CPU的资源
 *
 * 解决方法： CompletionService和CompletableFuture(能够实现按照任务完成的先后顺序获取任务的结果)
 **/
public class FutureTest {

    private static final String commandstr01 = "hahah";
    private static final String commandstr02 = "hahah";

    public static void main(String[] args) throws Exception {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future1 = executorService.submit(new MyThread1(commandstr01));
        Future<String> future2 = executorService.submit(new MyThread2(commandstr02));

        System.out.println(future1.get());
        System.out.println(future2.get());

        executorService.shutdown();
    }
}