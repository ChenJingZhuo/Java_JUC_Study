package com.cjz.function;

import java.util.function.Predicate;

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 */
public class Demo02 {
    public static void main(String[] args) {
        /*Predicate<String> predicate = new Predicate<String>() {
            //判断字符串是否为空
            @Override
            public boolean test(String str) {
                return str.isEmpty();
            }
        };*/

        Predicate<String> predicate = str->{//判断字符串是否为空
            return str.isEmpty();
        };

        //System.out.println(predicate.test(null));//java.lang.NullPointerException
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("Hello World"));
    }
}
