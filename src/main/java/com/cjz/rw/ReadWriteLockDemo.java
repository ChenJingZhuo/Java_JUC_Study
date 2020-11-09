package com.cjz.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 独占锁（写锁）一次只能被一个线程
 * 共享锁（读锁）
 * 读-读 可以共存!
 * 读-写 不能共存！
 * 写-写 不能共存！
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //写入
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(finalI +"", finalI +"");
            },String.valueOf(i)).start();
        }

        //读取
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.get(finalI +"");
            }).start();
        }

    }

}

/**
 * 自定义缓存
 */
class MyCache{

    private volatile Map<String, Object> map = new HashMap<>();

    //存，写
    public void put(String key, Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName()+"写入OK");
    }

    //取，读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }

}

//加锁的
class MyCacheLock{

    private volatile Map<String, Object> map = new HashMap<>();
    //读写锁：更加细粒度的控制
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //存，写入的时候，只希望同时只有一个线程写
    public void put(String key, Object value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    //取，读，所有人都可以读！
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取OK");
    }

}
