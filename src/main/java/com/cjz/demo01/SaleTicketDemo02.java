package com.cjz.demo01;

import java.util.concurrent.locks.ReentrantLock;

//基本的卖票例子

/**
 * 真正的多线程开发、公司中的开发
 * 线程就是一个单独的资源类，没有任何附属的操作！
 * 1.属性、方法
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //多线程操作
        //并发：多线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket = new Ticket2();

        //@FunctionalInterface 函数式接口 jdk1.8 lambda表达式 (参数)->{ 代码 }
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"B").start();
        new Thread(()->{ for (int i = 0; i < 60; i++) ticket.sale(); },"C").start();
    }
}

//资源类OOP（降低耦合性）
//Lock三部曲
//1、new ReentrantLock();
//2、lock.lock();//加锁
//3、lock.unlock();//解锁
class Ticket2{
    //属性、方法
    private int number = 50;

    private ReentrantLock lock = new ReentrantLock();

    //买票的方式
    public synchronized void sale(){
        lock.lock();//加锁
        try {   //业务代码
            if (number > 0){
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(number--)+"票，剩余："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//解锁
        }
    }

}
