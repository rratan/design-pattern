package org.rratan.concurrency.algos.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BFS_MULTITHREADED <T extends BaseNode>{
        Queue<T> qe = new LinkedList<>();
        T root;
        ReentrantReadWriteLock re = new ReentrantReadWriteLock();
        Lock writelock = re.writeLock();
        Lock readlock = re.readLock();

        public BFS_MULTITHREADED(T root){
            this.root = root;
            qe.add(root);
        }

        public T getNode(){
            T node;
            try {
                while(!writelock.tryLock()){
                    Thread.sleep(1000);
                }
                node = qe.poll();

                System.out.println(Thread.currentThread().getName()+" "+ (node!=null?node.getVal():"null"));
            }catch (InterruptedException e){
                node =null;
            }finally {
                writelock.unlock();
            }
            return  node;
        }

        public boolean ifEmpty(){
            boolean empty;
            try{
                while(!readlock.tryLock()){
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"Read lock not available");
                }

                empty = qe.isEmpty();
            }catch (InterruptedException e){
              empty = true;
            } finally {
                readlock.unlock();
            }
            return empty;
        }


        public void bfs(){
            while(true){
                if(ifEmpty()){
                    System.out.println("Nothing to process.Returning");
                    return;
                }
                T temp = getNode();
                if(temp==null){
                    continue;
                }
                System.out.println(Thread.currentThread().getName());
                for(int i=0;i<temp.getPointer().size();i++){
                    qe.offer((T)temp.getPointer().get(i));
                }

            }
        }

        public static void main(String[] args) throws Exception{
            TreeNode root = new TreeNode(10);
            root.add(new TreeNode(20));
            root.add(new TreeNode(40));
            root.add(new TreeNode(50));
            root.getPointer().get(0).add(new TreeNode(60));
            root.getPointer().get(0).add(new TreeNode(70));
            root.getPointer().get(0).add(new TreeNode(80));
            root.getPointer().get(1).add(new TreeNode(90));
            root.getPointer().get(1).add(new TreeNode(1000));
            BFS_MULTITHREADED<TreeNode> bb = new BFS_MULTITHREADED<>(root);
            ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            System.out.println(Runtime.getRuntime().availableProcessors());

            Future f =ex.submit(()-> bb.bfs());
            Future f1 = ex.submit(()-> bb.bfs());
            Future f2 =  ex.submit(()-> bb.bfs());
            f.get();
            f1.get();
            f2.get();


            ex.shutdown();
        }
}
