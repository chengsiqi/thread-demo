package com.example.concurrent.callable;

import java.util.concurrent.*;

/**
 * @author csq
 * @date 2020/4/16 17:02
 * @description
 *
 * Java 7中还提供了分支/合并（fork/join）框架，它可以实现线程池中任务的自动调度，并且这种调度对
 * 用户来说是透明的。为了达到这种效果，必须按照用户指定的方式对任务进行分解，然后再将分解出的小型任
 * 务的执行结果合并成原来任务的执行结果。这显然是运用了分治法（divide-and-conquer）的思想。
 * 下面的代码使用了分支/合并框架来计算1到10000的和，当然对于如此简单的任务根本不需要分支/合并框架，
 * 因为分支和合并本身也会带来一定的开销，但是这里我们只是探索一下在代码中如何使用分支/合并框架，
 * 让我们的代码能够充分利用现代多核CPU的强大运算能力。
 **/

class Calculator extends RecursiveTask<Integer>{

    private static final long serialVersionUID = 7333472779649130114L;

    private static final int THRESHOLD = 10;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 当问题分解到可求解程度时直接计算结果
        if((end - start) < THRESHOLD){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else {
            int middle = (start + end) >>> 1;
            // 将任务一分为二
            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();
            // 注意： 由于此处是递归式的任务分解, 也就意味着接下来会二分为四, 四分为八...

            // 合并两个子任务的结果
            sum = left.join() +right.join();
        }

        return sum;
    }
}

public class Test1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new Calculator(1, 10000));
        System.out.println(result.get());

        // 伴随着Java 7的到来，Java中默认的数组排序算法已经不再是经典的快速排序（双枢轴快速排序）了，
        // 新的排序算法叫TimSort，它是归并排序和插入排序的混合体，TimSort可以通过分支合并框架
        // 充分利用现代处理器的多核特性，从而获得更好的性能（更短的排序时间）。
    }
}
