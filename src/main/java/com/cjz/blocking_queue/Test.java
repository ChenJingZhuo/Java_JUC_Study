package com.cjz.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.1 抛出异常     blockingQueue.add("a") 添加
     * 1.2 抛出异常     blockingQueue.remove() 移除
     */
    public static void test1(){
        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //java.lang.IllegalStateException: Queue full 队列已满 抛出异常!
        //System.out.println(blockingQueue.add("d"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //java.util.NoSuchElementException 队列为空 抛出异常！
        System.out.println(blockingQueue.remove());
    }

    /**
     * 2.1 不抛出异常    blockingQueue.offer("a") 添加
     * 2.2 不抛出异常    blockingQueue.poll() 移除
     * 2.3 检查队列首元素 blockingQueue.element() 抛出异常
     * 2.4 检查队列首元素 blockingQueue.peek() 不抛出异常
     */
    public static void test2(){
        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.offer("d"));//false 不抛出异常


        //java.util.NoSuchElementException 若队列为空，则抛出异常
        System.out.println(blockingQueue.element());//不为空，则返回队首元素

        //若队列为空，则返回null
        System.out.println(blockingQueue.peek());//不为空，则返回队首元素


        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll());//null 不抛出异常
    }

    /**
     * 3.1 等待，阻塞（一直阻塞）blockingQueue.put("a") 添加
     * 3.2 等待，阻塞（一直阻塞）blockingQueue.take()   移除
     */
    public static void test3() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //一直阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        blockingQueue.put("d");//队列没有位置了，一直阻塞

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        System.out.println(blockingQueue.take());//没有这个元素，一直阻塞
    }

    /**
     * 4.1 等待，阻塞（超时等待） blockingQueue.offer("d", 2, TimeUnit.SECONDS) 添加
     * 4.2 等待，阻塞（超时等待） blockingQueue.poll(2, TimeUnit.SECONDS)       移除
     */
    public static void test4() throws InterruptedException {
        //队列的大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        //超时等待
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");

        blockingQueue.offer("d", 2, TimeUnit.SECONDS);//等待超过2秒就退出

        System.out.println("========");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));//等待超过2秒就退出
    }
}
