package org.rratan.ratelimiter.impl.tokenBucket;

import org.rratan.ratelimiter.skeleton.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;


public class TokenBucketRateLimiter implements RateLimiter {
        private ConcurrentHashMap<String,Bucket> reqstrBucket = new ConcurrentHashMap<>();
        private long TTL = 1000L;
        private double TOKEN_FILL_RATE = 0.5;
        private int TOKEN_LIMIT = 10;

        public TokenBucketRateLimiter(){

        }

        @Override
        public boolean acceptRequests(String reqstId){
                Bucket reqstBucket = reqstrBucket.compute(reqstId, (K, V)->{
                        Bucket b = V;
                        if(b==null){
                                b = new Bucket(TOKEN_LIMIT,TTL,TOKEN_FILL_RATE);
                        }

                        return b;
                });

                if(reqstBucket.tryAcquire()){
                        System.out.println("Request accepted");
                        return true;
                }
                System.out.println("Limit Breached.Reqest Rejected");
                return false;
        }
}
