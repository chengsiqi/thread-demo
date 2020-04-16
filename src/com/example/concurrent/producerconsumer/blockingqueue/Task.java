package com.example.concurrent.producerconsumer.blockingqueue;

import java.util.UUID;

/**
 * @author csq
 * @date 2020/4/16 10:16
 * @description
 *
 * 工作任务类
 **/
public class Task {
    // 任务编号
    private String id;

    public Task() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                '}';
    }
}
