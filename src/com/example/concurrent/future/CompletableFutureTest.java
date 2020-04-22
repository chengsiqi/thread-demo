package com.example.concurrent.future;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author csq
 * @date 2020/4/21 14:05
 * @description
 *
 *
 * CompletableFuture接口
 * 所谓异步调用其实就是实现一个可无需等待被调函数的返回值而让操作继续运行的方法.
 * 简单的讲就是另启一个线程来完成调用中的部分计算, 使调用继续运行或返回, 而不需要等待计算结果.但调用者仍需要取线程的计算结果
 *
 * JDK1.5新增了Future接口, 用于描述一个异步计算的结果。虽然Future以及相关使用方法提供了异步执行任务的能力,
 * 但是对于结果的获取却是很不方便, 只能通过阻塞或者轮询的方式得到任务的结果。
 *
 * JDK1.8后提出了CompletableFuture类实现了Future和CompletionStage两个接口,
 * CompletionStage可以看做是一个异步任务执行过程的抽象(CompletionStage代表异步计算过程中的某一个阶段,
 * 一个阶段完成以后可能会触发另外一个阶段, 一个阶段的计算执行可以是一个Function, Consumer或者Runnable)
 *
 * 我们可以基于CompletableFuture创建任务和链式处理多个任务, 并实现按照任务完成的先后顺序获取任务的结果
 *
 * 1)、创建任务
 *  ##使用runAsync方法新建一个线程来运行Runnable对象(无返回值)
 *  ##使用supplyAsync方法新建线程来运行Supplier<T>对象(有返回值)
 *  ##基于线程池创建
 *
 * 2)、任务的异步处理
 *  不论Future的get()方法还是CompletableFuture的get()方法都是阻塞的, 为了获取任务的结果同时不阻塞当前线程的执行,
 *  我们可以使用CompletionStage提供的方法结合callback来实现任务的异步处理
 *  ##whenComplete：是执行当前任务的线程执行继续执行whenComplete的任务
 *  ##whenCompleteAsync：把whenCompleteAsync这个任务继续提交给线程池来进行执行, 也就是并行执行
 *  ##thenApply：当一个线程依赖另一个线程时, 可以使用thenApply方法来把两个线程串行化
 *  ##thenAccept：thenAccept接收上一阶段的输出作为本阶段的输入，并消费处理，无返回结果。　
 *  ##thenRun：不关心前一阶段的计算结果，因为它不需要输入参数，进行消费处理，无返回结果。
 *  ##thenCombine：会把两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
 *  ##applyToEither：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
 *  ##acceptEither：两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作
 *
 *  在CompletableFuture接口中除了使用whenComplete还可以使用handle等方法能实现按照任务完成的先后顺序获取任务的结果。
 *
 **/
public class CompletableFutureTest {

    private static final String commandStr01 = "hahah";
    private static final String commandStr02 = "hahah";
    private static final String commandStr03 = "hahah";
    private static final String commandStr04 = "hahah";
    private static final String commandStr05 = "hahah";

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        CompletableFuture.supplyAsync(new MyThread4(commandStr04), executorService)
                .whenComplete(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String result, Throwable e) {
                        // 执行线程执行完以后的操作
                        System.out.println(result + " " + e);
                    }
                }).exceptionally(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) {
                        // 抛出异常
                        System.out.println("exception " + throwable);
                        return "exception";
                    }
        });

        CompletableFuture.supplyAsync(new MyThread3(commandStr03), executorService)
                .whenComplete(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String result, Throwable throwable) {
                        // 执行线程执行完以后的操作
                        System.out.println(result + " " + throwable);
                    }
                }).exceptionally(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) {
                        System.out.println("exception " + throwable);
                        return "exception";
                    }
        });

        executorService.shutdown();
    }
}

class MyThread3 implements Supplier<String> {
    // 要运行的命令
    private String commandStr;

    public MyThread3(String commandStr) {
        this.commandStr = commandStr;
    }

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 30; i++) {

            // i = i/0;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println("MyThread3: " + i);
        }

        return String.valueOf(sum + 300000);
    }
}

class MyThread4 implements Supplier<String> {
    // 要运行的命令
    private String commandStr;

    public MyThread4(String commandStr) {
        this.commandStr = commandStr;
    }

    @Override
    public String get() {
        int sum = 0;
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += i;
            System.out.println("MyThread4: " + i);
        }

        return String.valueOf(sum + 400000);
    }
}