package com.cjz.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//ConcurrentModificationException
public class MapTest {

    public static void main(String[] args) {
        //map 是这样用的吗？
        //Map<String, String> map = new HashMap<>();
        //默认等于什么？new HashMap<>(16,0.75);
        //唯一的一个家庭作业：研究
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }

}
