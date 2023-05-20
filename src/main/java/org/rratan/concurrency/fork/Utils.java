package org.rratan.concurrency.fork;


import java.util.concurrent.TimeUnit;

public class Utils {

    public static void delay(long time) {
        try {
            Thread.sleep(time);
        }catch (InterruptedException e){
            System.out.println("Thread interrupted from sleep");
        }

    }
}
