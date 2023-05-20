package org.rratan.concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {
    public static void main(String[] args) throws InterruptedException{
        LeakyBucket_Impl bucket = new LeakyBucket_Impl(50);
        Client cln = new Client(bucket);
        Server srv = new Server(bucket);
        Thread t1 = new Thread(cln);
        Thread t2 = new Thread(srv);
        Thread t3 = new Thread(srv);


//        t1.join();
            t2.start();
            t3.start();
        t1.start();
    }
}

class Client implements Runnable{
    private LeakyBucket_Impl bucket;
    public Client(LeakyBucket_Impl bucket){
        this.bucket = bucket;
    }

    public void run(){
        int i=100;
        while (i>0){
            Packet p = new Packet(Integer.toString(i));
            bucket.addRequest(p);
            if(i%10==0){
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){

                }
            }
            i--;

        }
    }
}

class Server implements Runnable{
    LeakyBucket_Impl bucket;

    public Server(LeakyBucket_Impl bucket){
        this.bucket = bucket;
    }

    public void run(){
        while(true){
            System.out.println(Thread.currentThread().getName());
            Packet resp = bucket.processRequest();
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }
    }
}

class LeakyBucket_Impl {
    private Queue<Packet> leakyBucket = new LinkedList<>();
    private int bucketSize;
    private int currSize;

    public LeakyBucket_Impl(int bucketSize){
        this.bucketSize = bucketSize;
        this.currSize = 0;
    }


     synchronized void addRequest(Packet p){
        if(currSize > bucketSize){
            System.out.println(Color.ANSI_BLUE+"Bucket is full. Packet will be leaked.");
            return;
        }
        if(p==null){
            System.out.println("Invalid Input");
            return;
        }
        currSize++;
         System.out.println(Color.ANSI_BLUE+"Adding");
        leakyBucket.add(p);
    }

     synchronized Packet processRequest(){
        if(currSize==0){
            System.out.println(Color.ANSI_GREEN+"Nothing to process");
            return null;
        }
         System.out.println(Color.ANSI_GREEN+"Removing");
        Packet p = leakyBucket.poll();
        --currSize;
        return p;
    }
}

class Packet{
    private String id;
    public Packet(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}


