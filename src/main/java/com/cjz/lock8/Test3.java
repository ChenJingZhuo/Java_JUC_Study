package com.cjz.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 7、1个静态的同步方法，1个普通的同步方法，一个对象，先打印发短信还是打电话？  打电话
 * 8、1个静态的同步方法，1个普通的同步方法，两个对象，先打印发短信还是打电话？  打电话
 */
public class Test3 {

    public static void main(String[] args) {
        /*Phone3 phone = new Phone3();

        //锁的存在，不是同一把锁，哪个资源占用少哪个先执行
        //先打电话，后发短信
        new Thread(()->{
            phone.sendSms();
        }).start();

        new Thread(()->{
            phone.call();
        }).start();*/


        //两个对象的Class类模板只有一个，static，锁的是Class
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

        //先打电话，后发短信
        new Thread(()->{
            phone1.sendSms();
        }).start();

        new Thread(()->{
            phone2.call();
        }).start();


    }

}

//Phone3.class唯一的一个Class对象
class Phone3{

    //静态的同步方法 锁的是Class类模板
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通的同步方法 锁的是方法的调用者
    public synchronized void call(){
        System.out.println("打电话");
    }

}