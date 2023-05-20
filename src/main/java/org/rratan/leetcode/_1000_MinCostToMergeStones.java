package org.rratan.leetcode;

import java.util.Arrays;

public class _1000_MinCostToMergeStones {
    /*
    There are n piles of stones arranged in a row. The ith pile has stones[i] stones.
    A move consists of merging exactly k consecutive piles into one pile,
     and the cost of this move is equal to the total number of stones in these k piles.

    Return the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.

    stones = [3,2,4,1], k = 2

    1 [2,k-1] -> 2 [
    2 [3,k-1]
    3 [4,k-1]
    4 [5,k-1]
    5 [6,k]


    Very Hard Problem
     */
    public int cal(int[][][] dp,int i,int j,int k,int[] prefixSum,int n){
        if(i==j && k==1){
            return 0;
        }
        if(i==j){
            return Integer.MAX_VALUE/4;
        }
        if(dp[i][j][k]!=-1){
            return dp[i][j][k];
        }
        int ans = Integer.MAX_VALUE;
        if(k==1){
            ans = cal(dp,i,j,n,prefixSum,n)+ (i==0?prefixSum[j]:prefixSum[j]-prefixSum[i-1]);
        }else{
            for(int l=i;l<j;l++){
                ans = Math.min(ans,cal(dp,i,l-1,1,prefixSum,n)+cal(dp,l+1,j,k-1,prefixSum,n));
            }
        }
        dp[i][j][k]=ans;
        return ans;
    }
    public int mergeStones(int[] stones, int k) {
            int n,i;
            n = stones.length;
            int[] prefix = new int[n];
            int[][][] dp = new int[n][n][k];
            prefix[0]=stones[0];

            for(i=1;i<n;i++){
                prefix[i]= prefix[i-1]+stones[i];
            }
            for(i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    Arrays.fill(dp[i][j],-1);
                }
            }

            return cal(dp,0,n-1,1,prefix,k);
    }
}
