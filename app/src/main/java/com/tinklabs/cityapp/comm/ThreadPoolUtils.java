package com.tinklabs.cityapp.comm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类，有耗时或者频繁操作非主线程可以使用该工具类
 *
 * 如需给线程起名字，请在run方法中第一行代码添加下面的代码
 *    Thread.currentThread().setName("线程名");
 */

public class ThreadPoolUtils {
    private static ExecutorService shortWork = Executors.newCachedThreadPool();
    private static ExecutorService normalWork = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
    private static ScheduledExecutorService scheduleWork = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 10);

    /**
     * 该方法用来添加耗时短，执行频繁的任务，需要立马执行的
     *
     * @param task 需要执行的任务
     */
    public static void addShortWork(Runnable task) {
        shortWork.submit(task);
    }

    /**
     * 该方法用来添加执行任务不是很频繁，耗时相对较长的任务，请勿在本线程池中添加阻塞线程，含有死循环的线程
     *
     * @param task 需要执行的任务
     */
    public static void addNormalWork(Runnable task) {
        normalWork.submit(task);
    }

    /**
     * 与addNormalWork方法类似，不过添加到这里的任务是反复按照一定时间间隔执行的
     *
     * @param task   需要执行的任务
     * @param period 周期执行的时间，单位为毫秒
     */
    public static void addScheduledWork(Runnable task, long period) {
        scheduleWork.scheduleAtFixedRate(task, 0, period, TimeUnit.MILLISECONDS);
    }

}
