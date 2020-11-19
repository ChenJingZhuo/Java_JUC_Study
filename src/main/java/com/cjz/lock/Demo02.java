package com.cjz.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//lock
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.call();
        },"B").start();

    }
}

class Phone2{

    Lock lock = new ReentrantLock();

    public void sms(){
        //lock.lock();
        lock.lock();// 细节问题：lock.lock(); lock.unlock();
        //lock锁必须配对，否则就会死锁
        try {
            System.out.println(Thread.currentThread().getName()+"=>sms");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
            lock.unlock();
        }

    }

    public void call(){
        lock.lock();// 细节问题：lock.lock(); lock.unlock();
        try {
            System.out.println(Thread.currentThread().getName()+"=>call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
