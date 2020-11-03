package com.cjz.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1、探究原理
 * 2、觉得自己会用
 */
public class CallableTest {

    public static void main(String[] args) {
        //new Thread(new MyThread()).start();
        //new Thread(new Runnable()).start();
        //new Thread(new FutureTask<V>()).start();
        //new Thread(new FutureTask<V>( Callable )).start();

        new Thread().start();//怎么启动Callable

        MyThread myThread = new MyThread();
        FutureTask futureTask = new FutureTask(myThread);//适配类

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();//有缓存，结果可能需要等待，阻塞

        try {
            Integer o = (Integer) futureTask.get();//获取Callable的返回结果
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}

//开辟一空间，没有返回值
//class MyThread implements Runnable {
//
//    @Override
//    public void run() {
//
//    }
//
//}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}
