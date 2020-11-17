package com.cjz.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

//解决ABA问题，引入原子引用！对应思想：乐观锁
public class CASDemo02 {

    //CAS compareAndSet : 比较并交换
    public static void main(String[] args) {
        //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
        //Integer范围：-128~127 超过这个范围，就会new新的对象，== 比较就会失败，更行就不成功。
        //正常在业务操作，这里面比较的都是一个个对象 原子引用
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            //int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println(Thread.currentThread().getName()+"1=>"+atomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(1,2,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);

            System.out.println(Thread.currentThread().getName()+"2=>"+atomicStampedReference.getStamp());

            System.out.println("a:"+atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println(Thread.currentThread().getName()+"3=>"+atomicStampedReference.getStamp());

        },"a").start();

        //乐观锁原理相同 ABA
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();//获得版本号
            System.out.println(Thread.currentThread().getName()+"1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b:"+atomicStampedReference.compareAndSet(1, 6, stamp, atomicStampedReference.getStamp() + 1));

            System.out.println(Thread.currentThread().getName()+"2=>"+atomicStampedReference.getStamp());

        },"b").start();

    }
}
