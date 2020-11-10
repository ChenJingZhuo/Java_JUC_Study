package com.cjz.pool;

import java.util.concurrent.*;

/**
 * Executors 工具类、3大方法
 * Executors.newSingleThreadExecutor();//单个线程
 * Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
 * Executors.newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱
 *
 * new ThreadPoolExecutor.AbortPolicy()    //拒绝策略，银行满了，还有人进来，不处理这个人，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy()    //拒绝策略，哪来的去哪里！银行满了，从main线程来，则让main线程执行
 * new ThreadPoolExecutor.DiscardPolicy()    //拒绝策略，队列满了，丢掉任务，不会抛出异常！
 * new ThreadPoolExecutor.DiscardOldestPolicy()    //拒绝策略，尝试去和最早的竞争，失败则丢掉，不会抛出异常！
 */
public class Demo03 {
    public static void main(String[] args) {
        //自定义线程池！工作 ThreadPoolExecutor

        //最大线程到底该如何定义
        //1.CPU 密集型，几核，就是几，可以保持CPU的效率最高！
        //2.IO 密集型  >   判断你程序中十分耗IO的线程
        //程序    15个大型任务 IO十分占用资源！

        //获取CPU线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()    //拒绝策略，银行满了，还有人进来，不处理这个人，抛出异常
                //new ThreadPoolExecutor.CallerRunsPolicy()    //拒绝策略，哪来的去哪里！银行满了，从main线程来，则让main线程执行
                //new ThreadPoolExecutor.DiscardPolicy()    //拒绝策略，队列满了，丢掉任务，不会抛出异常！
                new ThreadPoolExecutor.DiscardOldestPolicy()    //拒绝策略，尝试去和最早的竞争，失败则丢掉，不会抛出异常！
        );

        try {
            //i<1
            //i<5
            //i<8
            //i<9
            //最大承载：BlockingQueue + maximumPoolSize
            //超出最大承载：RejectedExecutionException 抛出异常
            for (int i = 0; i < 9; i++) {
                //使用了线程池之后，使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
