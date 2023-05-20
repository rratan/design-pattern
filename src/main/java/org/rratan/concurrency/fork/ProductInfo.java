package org.rratan.concurrency.fork;

import java.util.List;

import static org.rratan.concurrency.fork.Utils.delay;

public class ProductInfo {

    public String getInfo(String id){
        delay(1000);
        return "info";
    }
}
