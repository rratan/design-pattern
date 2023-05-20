package org.rratan.ratelimiter.skeleton;


public interface RateLimiter {
     boolean acceptRequests(String ClientId);
}
