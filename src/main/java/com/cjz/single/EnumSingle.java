package com.cjz.single;

import java.lang.reflect.Constructor;

//enum是什么？本身也是一个Class类
public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }

}

class Test{

    public static void main(String[] args) throws Exception {
        /*//1
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        //java.lang.NoSuchMethodException: com.cjz.single.EnumSingle.<init>()   没有这个空参构造方法
        System.out.println(instance1);
        System.out.println(instance2);*/

        //2
        EnumSingle instance1 = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        //java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        System.out.println(instance1);
        System.out.println(instance2);

    }

}
