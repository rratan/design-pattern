package org.rratan.leetcode;

import java.util.*;

public class _767_ReorganizeStrings {
    public String solveUsingPQ(char[] tasks,int n) {

        HashMap<Character,Integer> mp = new HashMap<>();
        for(Character ch: tasks){
            mp.put(ch,mp.getOrDefault(ch,0)+1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue()-a.getValue());
        pq.addAll(mp.entrySet());

        int ans = 0,i = 0,j=0;
        StringBuilder sb = new StringBuilder();
        List<Map.Entry<Character,Integer>> lis = new ArrayList<>();
        while(!pq.isEmpty()){

            Map.Entry<Character,Integer> temp = pq.poll();
            temp.setValue(temp.getValue()-1);
            sb.append(temp.getKey());
            pq.addAll(lis);
            lis = new ArrayList<>();
            if(temp.getValue()!=0){
                lis.add(temp);
            }

            ans++;


        }
        if(ans==n){
            return sb.toString();
        }
        return  "";
    }

    public String reorganizeString(String s) {
        char[] tasks = s.toCharArray();
        return solveUsingPQ(tasks,s.length());
    }

}
