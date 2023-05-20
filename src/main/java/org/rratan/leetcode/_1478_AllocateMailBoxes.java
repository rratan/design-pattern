package org.rratan.leetcode;

import java.util.Arrays;

public class _1478_AllocateMailBoxes {

    public int getMedian(int[] houses,int i,int j){
        int ans = 0;
        int mid = (j+i)/2;
        for(int k=i;k<=j;k++){
            ans+= Math.abs(houses[k]-houses[mid]);
        }
        return ans;
    }

    public int cal(int[][] dp,int i,int k,int n,int[] houses){
        if(i==n && k==0){
            return 0;
        }

        if(k==0 || i>=n){
            return Integer.MAX_VALUE/2;
        }
        if(dp[i][k]!=-1){
            return dp[i][k];
        }
        int ans = Integer.MAX_VALUE;
        for(int j=i;j<n;j++){
            ans = Math.min(ans,getMedian(houses,i,j)+cal(dp,j+1,k-1,n,houses));
        }
        dp[i][k]=ans;
        return ans;
    }

    public int minDistance(int[] houses, int k) {
        int n = houses.length, j = 0;
        int[][] dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        return cal(dp,0,k,n,houses);
    }
}
