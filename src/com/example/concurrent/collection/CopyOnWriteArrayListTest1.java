package com.example.concurrent.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csq
 * @date 2020/4/14 16:57
 * @description
 *
 * CopyOnWriteArrayList是ArrayList在并发环境下的替代品。CopyOnWriteArrayList通过增加写时复制语义
 * 来避免并发访问引起的问题, 也就是说任何修改操作都会在底层创建一个列表的副本, 也就意味着之前已有的迭代器
 * 不会碰到意料之外的修改。这种方式对于不要严格读写同步的场景非常有用，因为它提供了更好的性能。
 * 记住，要尽量减少锁的使用，因为那势必带来性能的下降（对数据库中数据的并发访问不也是如此吗？
 * 如果可以的话就应该放弃悲观锁而使用乐观锁），CopyOnWriteArrayList很明显也是通过牺牲空间获得了时间
 * （在计算机的世界里，时间和空间通常是不可调和的矛盾，可以牺牲空间来提升效率获得时间，当然也可以通过牺牲时间来减少对空间的使用）。
 **/
public class CopyOnWriteArrayListTest1 {

    private static final int THREAD_POOL_SIZE = 2;

    // private static List<Double> list = new CopyOnWriteArrayList();

    public static void main(String [] args) throws InterruptedException {
        List<Double> list = new ArrayList();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++){
                    list.add(Math.random());
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        service.execute(runnable);
        service.execute(runnable);
        service.shutdown();

        // 上面的代码会在运行时产生ArrayIndexOutOfBoundsException，试一试将上面代码28行的ArrayList换成CopyOnWriteArrayList再重新运行。
    }
}
