package org.rratan.concurrency.fork;

import javax.sound.midi.Soundbank;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Product {

    public void get(){
        ProductInfo p = new ProductInfo();
        ProductReview pr = new ProductReview();
        ProductInfoService p1 = new ProductInfoService(p,"1");
        ProductReviewService p2 = new ProductReviewService(pr,"2");
        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<String > f1 = ex.submit(() -> p.getInfo("sad"));
        Future<String > f2 = ex.submit(()-> pr.getReview("ad"));

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        long a = System.currentTimeMillis();
        System.out.println(a);
//        t1.start();
//        t2.start();
        try {
//            t1.join();
//            t2.join();
            System.out.println(f1.get()+" " + f2.get());
        }catch (InterruptedException e){

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }finally {
            ex.shutdown();
        }

//        System.out.println(p.getInfo("a")+" "+pr.getReview("ad"));
        System.out.println(System.currentTimeMillis()-a);


    }
    public static void main(String[] args){
        Product p = new Product();
        p.get();
    }


    private class ProductInfoService implements Runnable{
        private ProductInfo p;
        private String ans;
        private String id;
        public ProductInfoService(ProductInfo p,String id){
            this.p = p;
            this.id = id;

        }

        @Override
        public void run() {
             ans = p.getInfo(id);
        }


        public String getAns() {
            return ans;
        }
    }


    private class ProductReviewService implements Runnable{
        private ProductReview p;
        private String ans;
        private String id;
        public ProductReviewService(ProductReview p,String id){
            this.p = p;
            this.id = id;

        }

        @Override
        public void run() {
            ans = p.getReview(id);
        }


        public String getAns() {
            return ans;
        }
    }
}
