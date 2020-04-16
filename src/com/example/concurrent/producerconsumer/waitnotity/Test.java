package com.example.concurrent.producerconsumer.waitnotity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author csq
 * @date 2020/4/16 9:59
 * @description
 **/
public class Test {

    public static void main(String[] args){
        List<Task> buffer = new ArrayList<>(Constants.MAX_BUFFER_SIZE);

        ExecutorService service = Executors.newFixedThreadPool(Constants.NUM_OF_PRODUCER + Constants.NUM_OF_CONSUMER);
        for (int i = 1; i <= Constants.NUM_OF_PRODUCER; i++){
            service.execute(new Producer(buffer));
        }

        for (int i = 1; i <= Constants.NUM_OF_CONSUMER; i++){
            service.execute(new Consumer(buffer));
        }
        service.shutdown();
    }
}
