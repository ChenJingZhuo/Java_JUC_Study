package com.cjz.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {

    //int 默认 0
    //Thread 默认 null
    //原子引用
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    //加锁
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"=>mylock");

        //自旋锁
        while (!atomicReference.compareAndSet(null, thread)){

        }

    }

    //解锁
    //加锁
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"=>myUnLock");
        atomicReference.compareAndSet(thread, null);
    }
}
