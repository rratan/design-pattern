package org.rratan.leetcode;

import java.util.*;

public class CuttOffGrass {
    public int findPathLen(List<List<Integer>> forest, int i, int j, int n, int m, int a, int b){
        Queue<int[]> qe = new LinkedList<>();
        HashMap<Integer,Boolean> mp =  new HashMap<>();
        qe.add(new int[]{i,j,0});
        mp.put(m*i+j,true);
        while(!qe.isEmpty()){
            int[] temp  = qe.poll();
            if(temp[0]==a && temp[1]==b){
                return temp[2];
            }
            if(i+1 < n && forest.get(i+1).get(j)!=0 && !mp.containsKey(m*(i+1)+j)){
                qe.add(new int[]{i+1,j,temp[2]+1});
                mp.put(m*(i+1)+j,true);

            }
            if(i-1 >=0 && forest.get(i-1).get(j)!=0 && !mp.containsKey(m*(i-1)+j)){
                qe.add(new int[]{i-1,j,temp[2]+1});
                mp.put(m*(i-1)+j,true);
            }
            if(j+1 < m && forest.get(i).get(j+1)!=0 && !mp.containsKey(m*i+j+1)){
                qe.add(new int[]{i,j+1,temp[2]+1});
                mp.put(m*i+j+1,true);
            }
            if(j-1 >=0 && forest.get(i).get(j-1)!=0 && !mp.containsKey(m*i+j-1)){
                qe.add(new int[]{i,j-1,temp[2]+1});
                mp.put(m*i+j-1,true);

            }
        }
        return -1;
    }

    public int cutOffTree(List<List<Integer>> forest) {
        int n,m,i,j;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a,int[] b){
                return a[0]-b[0];
            }
        });

        return 0;
    }
}
