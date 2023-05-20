package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _406_QueueConstructionByHeight {

    class Mypair implements  Comparable<Mypair>{
        int height;
        int greater;
        int g2;
        public Mypair(int height,int greater){
            this.height = height;
            this.greater = greater;
            this.g2 = greater;
        }

        @Override
        public int compareTo(Mypair o) {
            if (o.height==this.height){
                return this.greater- o.greater;
            }
            return this.height-o.height;
        }
    }
    public void constructSEGTree(int[] tree,int index,int l,int hi,int val){
        if(l==hi){
            tree[index]=val;
        }
        int mid = (l+hi)/2;
        constructSEGTree(tree,2*index,l,mid,val);
        constructSEGTree(tree,2*index+1,mid+1,hi,val);
        tree[index] = tree[2*index]+tree[2*index+1];
        return;
    }
    public void findIndex(int[] tree, int l, int hi, Mypair p , int index, Mypair[] ans){
        if(l==hi){
            ans[l]=p;
            tree[index]=0;
            return;
        }
        if(p.greater > tree[index]){
            return;
        }
        int mid = (l+hi)/2;
        if(tree[2*index]>=p.greater){
            findIndex(tree,l,mid,p,2*index,ans);
        }else{
            p.greater = p.greater  - tree[2*index];
            findIndex(tree,mid+1,hi,p,2*index+1,ans);
        }
        tree[index]--;
        return;
    }
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        int[][] ans = new int[n][n];
        int[] tree = new int[4*n];
        Mypair[] aa = new Mypair[n+1];
        Mypair[] inp = new Mypair[n];
        for(int i=0;i<n;i++){
            inp[i] = new Mypair(people[i][0],people[i][1]);
        }
        Arrays.sort(inp);
        constructSEGTree(tree,0,0,n-1,1);
        for(int i=0;i<n;i++){
            findIndex(tree,0,n-1,inp[i],0,aa);
        }
        for(int i=0;i<n;i++){
            System.out.printf(aa[i].height+" "+aa[i].g2);
            ans[i][0]=aa[i].height;
            ans[i][1]=aa[i].g2;
        }
        return ans;
    }
}
