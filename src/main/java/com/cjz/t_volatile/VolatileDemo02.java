package com.cjz.t_volatile;

import java.util.concurrent.atomic.AtomicInteger;

//不保证原子性
public class VolatileDemo02 {

    //volatile不保证原子性
    //private volatile static int num = 0;
    //原子类的Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
        //num++;//不是一个原子性操作
        num.getAndIncrement();//AtomicInteger + 1 方法，CAS
    }

    /*//synchronized可以保证原子性
    public synchronized static void add(){
        num++;
    }*/

    public static void main(String[] args) {
        //理论上num结果应该为2万
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        //等待其他线程执行完
        while (Thread.activeCount()>2){//main gc
            Thread.yield();//线程礼让
        }

        System.out.println(Thread.currentThread().getName()+" "+num);

    }
}
