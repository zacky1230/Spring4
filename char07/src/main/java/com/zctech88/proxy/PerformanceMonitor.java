package com.zctech88.proxy;

import java.lang.reflect.Method;

public class PerformanceMonitor {
    private static ThreadLocal<MethodPerformance> performancRecord = new
            ThreadLocal<MethodPerformance>();
    public static void begin(String method) {
        System.out.println("begin monitor...");
        MethodPerformance mp = new MethodPerformance(method);
        performancRecord.set(mp);
    }

    public static void end() {
        System.out.println("end monitor...");
        MethodPerformance mp = performancRecord.get();
        mp.printPerformance();
    }
}
