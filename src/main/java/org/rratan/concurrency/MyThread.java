package org.rratan.concurrency;

import static org.rratan.concurrency.Utils.getCurrTimeStamp;

public class MyThread extends  Thread{
    @Override
    public void run(){
        System.out.println(getCurrTimeStamp()+Thread.currentThread().getName());
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Something went wrong");
        }
    }
}
