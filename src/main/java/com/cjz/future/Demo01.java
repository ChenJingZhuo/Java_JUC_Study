package com.cjz.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调：Ajax
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*//发起一个请求
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                //模拟延时
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"=>runAsync=>Void");
        });

        System.out.println("1111");

        completableFuture.get();//获取阻塞执行结果*/

        //有返回值的supplyAsync异步回调
        //ajax，成功和失败的回调
        //返回的是错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"=>supplyAsync=>Integer");
            //int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t);//正常的返回结果，则u为null
            System.out.println("u=>" + u);//有错误，返回错误信息。java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            //e.printStackTrace();//堆栈信息
            System.out.println(e.getMessage());//可以获取到错误的返回结果。java.lang.ArithmeticException: / by zero
            return 233;
        }).get());

    }
}
