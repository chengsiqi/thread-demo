package com.example.concurrent.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author csq
 * @date 2020/4/16 16:39
 * @description
 *
 * java5 中还引入了Callable接口、Future接口和FutureTask接口
 *
 * Callable接口也是一个单方法接口,显然这是一个回调方法, 类似于函数式编程中的回调函数
 * 在Java8以前, Java中还不能使用Lambda表达式来简化这种函数式编程. 和Runnable接口不同的是
 * Callable接口的回调方法call方法会返回一个对象, 这个对象可以用将来时的方式在线程执行结束的时候获得信息
 *
 * 目前最新的Java版本中，Callable接口和Runnable接口都被打上了@FunctionalInterface的注解，也就是说它可以用函数式编程的方式（Lambda表达式）创建接口对象。
 *
 * Future接口的主要方法
 *  1). get()： 获取结果, 如果结果还没有准备好, get方法会阻塞直到取得结果；当然也可以通过参数设置阻塞超时时间
 *  2). cancel() ： 在运算结束前取消
 *  3). isDone() ： 可以用来判断运算是否结束
 **/
public class Test {

    private static final int POOL_SIZE = 10;

    static class CalcThread implements Callable<Double>{

        private List<Double> dataList = new ArrayList<>();

        public CalcThread() {
            for (int i = 0; i < 10000; i++){
                dataList.add(Math.random());
            }
        }

        @Override
        public Double call() throws Exception {
            double total = 0;

            // total = 5 / 0;

            for (Double d : dataList){
                total += d;
            }
            return total/dataList.size();
        }
    }

    public static void main(String[] args){
        List<Future<Double>> fList = new ArrayList<>();
        ExecutorService service = Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++){
            fList.add(service.submit(new CalcThread()));
        }

        for (Future<Double> f : fList){
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();

        // 上面代码中的call方法就是将计算出的10000个0到1之间的随机小数的平均值返回，我们通过一个Future接口的对象得到了这个返回值。

        /*ExecutorService service1 = Executors.newFixedThreadPool(1);
        while (true){
            Future<Double> future = service1.submit(new CalcThread());
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }*/
    }
}
