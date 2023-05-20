package org.rratan.ratelimiter;

import org.rratan.ratelimiter.impl.tokenBucket.TokenBucketRateLimiter;

public class driver {
    public static void  main(String[] args) throws InterruptedException{
        TokenBucketRateLimiter tf = new TokenBucketRateLimiter();

        String[] req = new String[]{"1","2","3","4","5","6","7","8","9","10"};

        int i,j;
        i=100;
        while (i>0){
            for(j=0;j< req.length;j++){
                tf.acceptRequests(req[j]);
                Thread.sleep(10);
            }

            i--;
        }
    }
}
