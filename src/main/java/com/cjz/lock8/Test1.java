package com.cjz.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1、标准情况下，两个线程先打印发短信还是打电话？    发短信
 * 2、sendSms延迟4s，两个线程先打印发短信还是打电话？发短信
 * 3、增加了一个普通方法后！先执行发短信还是hello？  普通方法
 * 4、两个对象，两个同步方法，发短信还是 打电话？     打电话
 */
public class Test1 {

    public static void main(String[] args) {
        //Phone phone = new Phone();

        /*
        //1
        //phone对象锁只有一个，谁先拿到谁先执行
        //先发短信，后打电话
        new Thread(() -> {
            phone.sendSms();
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }).start();*/


        /*
        //普通方法
        Phone phone = new Phone();

        //没有锁，不会等锁释放后拿到锁再执行
        //先hello，发短信
        new Thread(() -> {
            phone.sendSms();
        }).start();

        new Thread(() -> {
            phone.hello();
        }).start();*/

        //两个对象
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        //synchronized 非公平锁 先执行资源占用少的线程，再执行资源占用多的线程。
        //先打电话，后发短信
        new Thread(() -> {
            phone1.sendSms();
        }).start();

        new Thread(() -> {
            phone2.call();
        }).start();


    }

}

class Phone{

    //synchronized 同步方法（非静态） 锁的对象是方法的调用者

    public synchronized void sendSms(){
        //2     //先发短信，后打电话
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁！不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }

}
