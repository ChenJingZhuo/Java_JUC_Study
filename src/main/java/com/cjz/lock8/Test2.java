package com.cjz.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 5、增加两个静态的同步方法，只有一个对象，先打印发短信还是打电话？    发短信
 * 6、两个对象！增加两个静态的同步方法，先打印发短信还是打电话？       发短信
 */
public class Test2 {

    public static void main(String[] args) {
        /*
        //先发短信后打电话
        Phone2 phone = new Phone2();

        new Thread(()->{
            phone.sendSms();
        }).start();

        new Thread(()->{
            phone.call();
        }).start();*/

        //两个对象的Class类模板只有一个，static，锁的是Class
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        //先发短信后打电话
        new Thread(()->{
            phone1.sendSms();
        }).start();

        new Thread(()->{
            phone2.call();
        }).start();

    }

}

//Phone2.class唯一的一个Class对象
class Phone2{

    //synchronized 同步方法（非静态） 锁的对象是方法的调用者
    //static 静态方法
    //类一加载就有了！锁的是Class（全局唯一，只有一个）
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call(){
        System.out.println("打电话");
    }

}
