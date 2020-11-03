package com.cjz.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//加法计数器
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /**
         * 集齐7颗龙珠召唤神龙
         */
        //召唤神龙的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功！");
        });

        //CyclicBarrier cyclicBarrier = new CyclicBarrier(8, () -> {
        //    System.out.println("召唤神龙成功！");
        //});

        for (int i = 1; i <= 7; i++) {

            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+ finalI +"个龙珠");
                try {
                    cyclicBarrier.await();  //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
