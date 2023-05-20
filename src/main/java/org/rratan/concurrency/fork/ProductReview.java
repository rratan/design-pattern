package org.rratan.concurrency.fork;

import static org.rratan.concurrency.fork.Utils.delay;

public class ProductReview {
    public String getReview(String id){
        delay(1000);
        return "good";
    }
}
