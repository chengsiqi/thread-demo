package com.example.concurrent.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author csq
 * @date 2020/4/14 17:21
 * @description
 *
 * ConcurrentHashMap是HashMap在并发环境下的版本，大家可能要问，既然已经可以通过
 * Collections.synchronizedMap获得线程安全的映射型容器，为什么还需要ConcurrentHashMap呢？
 * 因为通过Collections工具类获得的线程安全的HashMap会在读写数据时对整个容器对象上锁，这样其它使用
 * 该容器的线程无论如何也无法再获取该对象的锁, 也就意味着要一直等待前一个获得锁的线程离开同步代码块之后
 * 才有机会执行。
 * 实际上, HashMap是通过哈希函数来确定存放键值对的桶(桶是为了解决哈希冲突而引入的), 修改HashMap时并
 * 不需要将整个容器锁住, 只需要锁住即将修改的“桶”就可以了。
 *
 * ConcurrentHashMap还提供了原子操作的方法：
 *  1). putIfAbsent ： 如果还没有对应的键值对映射,就将其添加到HashMap中
 *  2). remove ： 如果键存在而且值与当前状态相等(equals比较结果为true), 则用原子方式移除该键值对映射
 *  3). replace ： 替换掉映射中元素的原子操作
 **/
public class ConcurrentHashMapTest {

    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
}
