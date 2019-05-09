package com.hilkr.page.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 * TODO
 *
 * @author hilkr
 */
public class ThreadUtils {

    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    public static void execute(Runnable runnable){
        es.submit(runnable);
    }
}
