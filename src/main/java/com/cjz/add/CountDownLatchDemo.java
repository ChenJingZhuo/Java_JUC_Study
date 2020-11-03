package com.cjz.add;

import java.util.concurrent.CountDownLatch;

//计数器 减法计数器
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //总数是6，必须要执行任务的时候，再使用！
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"=>Go out");
                countDownLatch.countDown();//数量-1
            },String.valueOf(i)).start();
        }

        //每次有线程调用countDown()数量-1，假设计数器变为0，countDownLatch.await()就会被唤醒，继续执行！
        countDownLatch.await();//等待计数器归零，然后再向下执行，不阻塞

        System.out.println("Close door");
    }

}
