package org.rratan.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrintConcurrentNumbers {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EO o = new EO();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future f1 = ex.submit(()-> {
            try {
                o.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Future f2 = ex.submit(()-> {
            try {
                o.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        f1.get();
        f2.get();
    }
}

class My{
    boolean bl=true;

}
class EO{
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private My isEven =new My() ;
    private  Integer i=0;
    public void printEven() throws InterruptedException {

            while (i < 200) {
                synchronized (isEven) {
                    while (!isEven.bl) {
                        isEven.wait();


                    }

                    System.out.println(ANSI_GREEN + i);
                    i++;
                    isEven.bl =  false;
                    isEven.notify();
                }
            }

    }

    public void printOdd() throws InterruptedException {
        while (i < 200) {
            synchronized (isEven) {
                while(isEven.bl) {
                    isEven.wait();


                }
                System.out.println(ANSI_YELLOW + i);
                i++;
                isEven.bl = true;
                isEven.notify();
            }
        }}
}
