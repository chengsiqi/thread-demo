package com.example.concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author csq
 * @date 2020/4/21 10:08
 * @description
 *
 * Java5 以前实现多线程有两种实现方法：
 *  一种是继承Thread类；一种是实现Runnable接口
 *  两种方式都要通过重写run()方法来定义线程的行为, 推荐使用后者，因为Java中的继承是单继承
 *  一个类有一个父类, 如果继承了Thread类就无法再继承其他类了, 显然使用Runnable接口更为灵活
 *
 * java5以后创建线程还有第三种方式：
 *  实现Callable接口, 该接口中的call方法可以在线程执行结束时产生一个返回值
 *
 **/

class MyTask implements Callable<Integer>{

    private int upperBounds;

    public MyTask(int upperBounds){
        this.upperBounds = upperBounds;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= upperBounds; i++){
            sum += i;
        }
        return sum;
    }
}

public class Test2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<Integer>> list = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++){
            list.add(service.submit(new MyTask((int) (Math.random()*100))));
        }

        int sum = 0;
        for (Future<Integer> future : list){
            // Future.get方法是一个阻塞方法，当submit提交多个任务时，只有所有任务都完成后，
            // 才能使用get按照任务的提交顺序得到返回结果，所以一般需要使用future.isDone先判断任务
            // 是否全部执行完成，完成后再使用future.get得到结果。
            // （也可以用get (long timeout, TimeUnit unit)方法可以设置超时时间，防止无限时间的等待）

            // while (!future.isDone());
            sum += future.get();
        }

        System.out.println(sum);

        service.shutdown();
    }
}
