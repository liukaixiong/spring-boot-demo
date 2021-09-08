package com.lkx.demo.springcloud.provider.utils;

public class ThreadLocalUtils {

    private static final ThreadLocal<String> context = new InheritableThreadLocal<>();

    public static void set(String val) {
        context.set(val);
    }

    public static String get() {
        return context.get();
    }

}
