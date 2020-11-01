package com.cjz.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {

    public static void main(String[] args) {
        Data3 data = new Data3();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"C").start();

        //用while代替if，避免虚假唤醒

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        },"D").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        },"E").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        },"F").start();


    }

}

//资源类 Lock
class Data3{

    private int number = 1;//1A 2B 3C

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void printA(){
        lock.lock();
        try {//业务，判断->执行->通知
            while (number != 1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>AAAA");
            //唤醒指定的线程，B
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {//业务，判断->执行->通知
            while (number != 2){
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>BBBB");
            //唤醒指定的线程，C
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {//业务，判断->执行->通知
            while (number != 3){
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>CCCC");
            //唤醒指定的线程，C
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}