package org.rratan.leetcode;

import java.util.Arrays;

public class _1306_jumpGame_3 {
    public int cal(int[] dp,int i,int n,int[] nums){
        if(i<0){
            return 0;
        }
        if(i>=n){
            return 0;
        }
        if(dp[i]!=-1){
            return dp[i];
        }
        int ans =0;
        if(nums[i]==0){
            dp[i]=1;
            return dp[i];
        }
        ans = Math.max(ans,Math.max(cal(dp,i+nums[i],n,nums),cal(dp,i-nums[i],n,nums)));
        dp[i]=ans;
        return dp[i];
    }

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return (cal(dp,start,n,arr)==1?true:false);
    }
}
