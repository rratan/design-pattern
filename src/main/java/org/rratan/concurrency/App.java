package org.rratan.concurrency;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args )
    {
        Thread t1 = new Thread(new MyRunnable());
        MyThread t2 = new MyThread();
        t1.setName("Runnable");
        t2.setName("Thread CLass");
        t1.start();
        try{
            t1.join(10);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println( "Hello World! 1");
        t2.start();
//        t2.join();
        System.out.println( "Hello World!" );
        try{
            t2.join();
        }catch (InterruptedException e){
            System.out.println(e);
        }

    }
}
