package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public static void recurseUtil(int n,int k,int curr,int val,List<Integer> perm,List<List<Integer>> ans){
        if(curr == k){
            ans.add(new ArrayList<>(perm));
            return;
        }
        if(val > n){
            return;
        }
        perm.add(val);
        recurseUtil(n,k,curr+1,val+1,perm,ans);
        perm.remove(new Integer(val));
        recurseUtil(n,k,curr,val+1,perm,ans);

    }
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        recurseUtil(n,k,0,1,perm,ans);
        return ans;

    }

    public static void main(String[] args){
        List<List<Integer>> ans = combine(1,2);
        for(List<Integer> perm: ans){
            for(int i=0; i<perm.size();i++){
                System.out.print(perm.get(i)+" ");
            }
            System.out.println();
        }
    }
}
