package com.example.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author csq
 * @date 2020/4/13 10:10
 * @description
 *
 * ID 序列生成器
 **/
public class IdGenerator {

    private final AtomicLong sequenceNumber = new AtomicLong(0);

    public long next(){
        return sequenceNumber.getAndIncrement();
    }


}
