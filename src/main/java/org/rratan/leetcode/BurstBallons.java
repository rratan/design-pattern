package org.rratan.leetcode;

import java.util.Arrays;

public class BurstBallons {
    /*
    You are given n balloons, indexed from 0 to n - 1.
     Each balloon is painted with a number on it represented by an array nums.
     You are asked to burst all the balloons.
    If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
     If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

Return the maximum coins you can collect by bursting the balloons wisely.
     */

    public int maxCoinsRecurse(int[][] dp,int i,int j,int[] inp){
        if(i>j){
            return 0;
        }
        if(dp[i][j]!=0){
            return dp[i][j];
        }
        int k,ans=0;
        for(k=i;k<=j;k++){
            ans = Math.max(ans,inp[i-1]*inp[k]*inp[j+1]+maxCoinsRecurse(dp,i,k-1,inp)+maxCoinsRecurse(dp,k+1,j,inp));
        }
        dp[i][j]=ans;
        return ans;
    }
    public int maxCoins(int[] nums) {
        int i , j , n, m;
        n = nums.length;
        int[] inp = new int[n+2];
        int[][] dp = new int[n+2][n+2];
        inp[0]=1;
        inp[n+1] = 1;
        for(i=1;i<=n;i++){
            inp[i]=nums[i-1];
        }
        return maxCoinsRecurse(dp,1,n,inp);

    }
    public static void main(String[] args){
        BurstBallons bb = new BurstBallons();
        System.out.println(bb.maxCoins(new int[]{3,1,5,8}));
    }
}
