package org.rratan.concurrency;


import java.util.ArrayList;
import java.util.List;

public class ConsumerProducer {
    public static void main(String[] args){
        Util up = new Util();
        (new Thread(new Producer(up))).start();
        (new Thread(new Consumer(up))).start();
        System.out.println("EXIT");
    }
}

class Util{
    private boolean isAvailable =false;
    private String data;
    public synchronized String read(){
        while(!isAvailable){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("ERROR");
            }
        }
        isAvailable = false;
        notifyAll();
        return data;
    }
    public synchronized void write(String val){
        while(isAvailable){
            try{
                wait();
            }catch (InterruptedException e){
                System.out.println("ERROR");
            }
        }
        data=val;
        isAvailable = true;
        notifyAll();
    }
}

class Producer implements Runnable{
    String[] temp = new String[]{"a","b","c"};
    Util up;
    public  Producer(Util up){
        this.up = up;
    }
    public void run(){
        for(int i=0;i<temp.length;i++ ){
            up.write(temp[i]);
//            try {
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//
//            }
        }
    }
}

class Consumer implements Runnable{

    Util up;
    public  Consumer(Util up){
        this.up = up;
    }
    public void run(){

       for(String it=up.read();!it.equals("c");it=up.read()){
           System.out.println(it);
        }
    }
}
