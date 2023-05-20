package org.rratan.ratelimiter.impl;

import org.rratan.ratelimiter.skeleton.RateLimiter;

import java.util.concurrent.*;

public class TokenRateLimiter implements RateLimiter {
    public static void main(String[] args) throws InterruptedException{
        RateLimiter my = new TokenRateLimiter();
        String[] client = {"1","2","3","4","5"};
        int l=100;
        int i=0;
        while(l>0){
            for(i=0;i<5;i++){
                if(my.acceptRequests(client[i])){
                    System.out.println("Request for id: "+client[i]+" is accepted");
                }else{
                    System.out.println("Request for id: "+client[i]+" is rejected");
                }
                Thread.sleep(1000);
            }
            l--;
        }
    }
    private final Long MAX_ALLOWED_REQESTS_CNT = 3L;
    private final Long TTL = 60L;
    private final Long TTL_BLCOKED = 10*60L;
    private final ConcurrentHashMap<String,RequestTracker> reqstrCounters = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String,RequestTracker> blockedRequests = new ConcurrentHashMap<>();
    private ScheduledExecutorService blockedReqst = Executors.newScheduledThreadPool(1);
    private ScheduledExecutorService reqstrCountersRefresh = Executors.newScheduledThreadPool(1);

    {
        blockedReqst.scheduleAtFixedRate( () -> {
            System.out.println("Service to clear reqstr map");
            long curSecond = System.currentTimeMillis() / 1000;
            reqstrCounters.entrySet().stream().filter(entry -> entry.getValue().getCurrTime() < curSecond).forEach(entry -> reqstrCounters.remove(entry.getKey()));
        },TTL,TTL, TimeUnit.SECONDS);

        reqstrCountersRefresh.scheduleAtFixedRate( () -> {
            System.out.println("Service to clear blocked reqst map");
            long curSecond = System.currentTimeMillis() / 1000;
            blockedRequests.entrySet().stream().filter(entry -> curSecond-entry.getValue().getCurrTime() > TTL_BLCOKED).forEach(entry -> blockedRequests.remove(entry.getKey()));
        },TTL_BLCOKED,TTL_BLCOKED, TimeUnit.SECONDS);
    }


    @Override
    public boolean acceptRequests(String clientId){
        if(blockedRequests.contains(clientId)){
            return false;
        }
        long curSecond = System.currentTimeMillis() / 1000;
        RequestTracker count = reqstrCounters.compute(clientId, (K,V)->{
            RequestTracker v = V;
            if(v==null || v.getCurrTime()>curSecond){
                v = new RequestTracker(curSecond);
            }
            v.setCounter(v.getCounter()+1L);
            return v;
        });
        if(count.getCounter()>MAX_ALLOWED_REQESTS_CNT){
            blockedRequests.put(clientId,count);
            return false;
        }
        return true;

    }
}

class RequestTracker{
    private long counter;
    private long currTime;

    public RequestTracker(long currTime){
        this(currTime,0L);
    }
    public RequestTracker(long currTime,long counter){
        this.currTime = currTime;
        this.counter = counter;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }
}
