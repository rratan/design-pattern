package org.rratan.leetcode;

import java.util.*;

public class _621_TaskScheduler {

    public int solveUsingPQ(char[] tasks,int n) {

        HashMap<Character,Integer> mp = new HashMap<>();
        for(Character ch: tasks){
            mp.put(ch,mp.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a,b) -> b.getValue()-a.getValue());
        pq.addAll(mp.entrySet());

        int ans = 0,i=0,j=0;
        while(!pq.isEmpty()){
            j = n+1;
            List<Map.Entry<Character,Integer>> lis = new ArrayList<>();
            while(j>0 && !pq.isEmpty()){
                Map.Entry<Character,Integer> temp = pq.poll();
                temp.setValue(temp.getValue()-1);
                if(temp.getValue()!=0){
                    lis.add(temp);
                }
                j--;
                ans++;
            }
            pq.addAll(lis);
           if(pq.isEmpty()){
             break;
           }
           ans+=j;
        }
        return  ans;
    }

    public int solveUsingGreedy(char[] tasks,int n) {
        HashMap<Character,Integer> mp = new HashMap<>();
        int max = 0 ,maxCount = 0;
        int m = tasks.length;
        for(Character ch: tasks){
            mp.put(ch,mp.getOrDefault(ch,0)+1);
            if(max<mp.get(ch)){
                max = mp.get(ch);
                maxCount=1;
            } else if (max == mp.get(ch)) {
                maxCount++;
            }
        }
        int emptySolts = (max-1)*(n-(maxCount-1));
        int idelSlots = Math.max(0,emptySolts-(m-(max*maxCount)));
        return m+idelSlots;


    }


    public int leastInterval(char[] tasks, int n) {
        return solveUsingGreedy(tasks,n);
    }
}
