package org.rratan.leetcode;

import java.util.*;

public class FindServersWithMaxRequests {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> ans = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        TreeSet<Integer> se = new TreeSet<>();

        int curr=0,i=0,j,n = arrival.length;
        for(i=0;i<k;i++){
            se.add(i);
        }
        i=0;
        int[] hitCnt = new int[k];
        while(i<n){
            curr = arrival[i];

            while(!pq.isEmpty() && pq.peek()[0]<=curr){

                int[] temp = pq.poll();
                System.out.println("VAlue:"+temp[0]+" "+temp[1]);
                se.add(new Integer(temp[1]));
            }
            j = i%k;
            if(se.isEmpty()){
                System.out.println("ERROR"+j);
                i++;continue;
            }
             if(se.contains(j)){
                 se.remove(j);
             }else{
                 Integer temp = se.ceiling(j);

                 if(temp==null){
                    j = se.first();
                 }else{
                     j=temp;
                 }

                 se.remove(j);
             }
//            se.add(j);
            System.out.println("SERVER"+j);
            pq.add(new int[]{curr+load[i],j});
            hitCnt[j]++;
            i++;
//            System.out.println("VAlue"+pq.poll()[0]);
        }

        int ma = 0;
        for(i=0;i<k;i++){
            ma=Math.max(ma,hitCnt[i]);
        }

        for(i=0;i<k;i++){
            if(hitCnt[i]==ma) {
                ans.add(i);
            }
        }

        return ans;

    }

    public static void main(String[] args){
        FindServersWithMaxRequests fp = new FindServersWithMaxRequests();
        fp.busiestServers(3,new int[]{1,2,3,4,5},new int[]{5,2,3,3,3});
    }
}
