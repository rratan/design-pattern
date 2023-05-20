package org.rratan.ratelimiter.impl.tokenBucket;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Bucket {
        private long TTL;
        private double TOKEN_FILL_RATE;
        private Semaphore tokens;
        private int TOKEN_LIMIT;
        private ScheduledExecutorService scheduler;


        public Bucket(int TOKEN_LIMIT,long TTL,double TOKEN_FILL_RATE){
            this.TTL = TTL;
            this.TOKEN_FILL_RATE = TOKEN_FILL_RATE;
            this.TOKEN_LIMIT = TOKEN_LIMIT;
            this.tokens = new Semaphore(TOKEN_LIMIT);
            this.scheduler = Executors.newScheduledThreadPool(1);
            scheduleTokenRefill();
        }

        private Bucket(){

        }

        public boolean tryAcquire(){
            return this.tokens.tryAcquire();
        }

        public void scheduleTokenRefill(){
            scheduler.scheduleAtFixedRate(()->{
                int temp = (int)((TOKEN_LIMIT-this.tokens.availablePermits())*this.TOKEN_FILL_RATE);
                System.out.println("Releasing tokens: "+temp);
                this.tokens.release(temp);
            },0,TTL, TimeUnit.MILLISECONDS);

        }





}
