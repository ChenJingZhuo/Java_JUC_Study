package com.cjz.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

//懒汉式单例
//道高一尺，魔高一丈
public class LazyMan {

    private static boolean cjz = false;

    private LazyMan(){
        synchronized (LazyMan.class){
            if(cjz == false){//2
                cjz = true;
                if (lazyMan!=null){
                    throw new RuntimeException("不要试图使用发射破坏单例模式");
                }
            } else {
                throw new RuntimeException("不要试图使用发射破坏单例模式");
            }

            /*if (lazyMan!=null){//1
                throw new RuntimeException("不要试图使用发射破坏单例模式");
            }*/
        }
    }

    private static LazyMan lazyMan;

    //双重检测锁模式的 懒汉式单例 DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null){
            synchronized (LazyMan.class){
                if (lazyMan == null){
                    lazyMan = new LazyMan();//不是一个原子性操作
                    /**
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 123
                     * 132 A
                     *     B //此时lazyMan还没有完成构造
                     *
                     */
                }
            }
        }
        return lazyMan;
    }

    //反射！
    public static void main(String[] args) throws Exception {
        /*//1
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);*/

        /*//2
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance = declaredConstructor.newInstance();
        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);*/

        //3 反编译知道加密的字段
        Field cjz = LazyMan.class.getDeclaredField("cjz");
        cjz.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance = declaredConstructor.newInstance();

        cjz.set(instance, false);

        LazyMan instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);

    }


}
