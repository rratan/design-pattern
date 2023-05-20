package org.rratan.leetcode;

public class _45_jumpGame {
    public int greeDy(int[] nums){
       int lower=0,upper=0,ans=0;
       int i,n;
       n = nums.length;
       i=0;
       while(i<n-1){
           upper = Math.max(upper,i+nums[i]);

           if(i==lower){
               ans++;
               lower = upper;
           }
       }
       return  ans;
    }
    public int jump(int[] nums) {
        int ans = 0;

        int n = nums.length;
        int i=0;
        int j=0,m;
        int[] dp = new int[n];
        dp[n-1] = 0;
        i=n-2;
        while(i>=0){
            j = i;
            m = Math.min(n-1,j+nums[j]);
            dp[i] = n;
            while(j<=m){

                dp[i] = Math.min(dp[i],dp[j]+1);
                j++;
            }

        }
        return dp[0];

    }
}
