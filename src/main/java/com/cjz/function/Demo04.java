package com.cjz.function;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口：没有参数，只有返回值
 */
public class Demo04 {
    public static void main(String[] args) {
        /*Supplier supplier = new Supplier() {
            @Override
            public Integer get() {
                System.out.println("get()");
                return 1024;
            }
        };*/

        Supplier supplier = ()->{
            System.out.println("get()");
            return 1024;
        };

        System.out.println(supplier.get());
    }
}
