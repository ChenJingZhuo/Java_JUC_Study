package com.cjz.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

//java.util.ConcurrentModificationException 并发修改异常
public class SetTest {

    public static void main(String[] args) {
        //并发下 HashSet 不安全的
        /**
         * 解决方案 同理
         * 2、Set<String> set = Collections.synchronizedSet(new HashSet<>());
         * 3、Set<String> set = new CopyOnWriteArraySet<>();
         */
        //Set<String> set = new HashSet<>();
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    /**
     * HashSet底层是什么？
     * HashMap
     * HashSet的add方法原理：
     * 本质就是map的key是无法重复的！
     */

    //public HashSet() {
    //    map = new HashMap<>();
    //}

    //public boolean add(E e) {
    //    return map.put(e, PRESENT)==null;
    //}

    //private static final Object PRESENT = new Object();

}
