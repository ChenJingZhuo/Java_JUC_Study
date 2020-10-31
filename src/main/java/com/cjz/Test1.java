package com.cjz;

import java.util.concurrent.TimeUnit;

public class Test1 {
    public static void main(String[] args) {
        //获取CPU的线程数
        //CPU密集型，IO密集型
        //4核8线程
        System.out.println(Runtime.getRuntime().availableProcessors());

        //企业一般不用Thread.sleep()，而用TimeUnit工具类
        //TimeUnit.DAYS.sleep(1);//睡一天
        //TimeUnit.SECONDS.sleep(1);//睡一秒

    }
}
