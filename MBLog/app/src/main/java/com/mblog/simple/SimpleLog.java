package com.mblog.simple;

import sj.mblog.L;

public class SimpleLog {

    /**
     * 假如希望在MBLog上封装一层自己的log
     * 那么你需要额外设置最后一个方法偏移的类名
     */
    static {
        L.setLastMethodClassName("com.mblog.simple.SimpleLog");
    }

    public static void d(Object... args) {
        L.d(args);
    }

    public static void e(Object... args) {
        L.e(args);
    }

    public static void e(Throwable throwable, Object... args) {
        L.e(throwable, args);
    }

    public static void w(Object... args) {
        L.w(args);
    }

    public static void i(Object... args) {
        L.i(args);
    }

    public static void v(Object... args) {
        L.v(args);
    }

    public static void wtf(Object... args) {
        L.wtf(args);
    }
}
