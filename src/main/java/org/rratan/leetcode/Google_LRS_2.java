package org.rratan.leetcode;

import java.util.Arrays;

public class Google_LRS_2 {

    public static int LRSUtil(int[][]dp,int i, int j, String s){
        if(i>=s.length() || j>=s.length()){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int ans = 0;
        if(s.charAt(i) == s.charAt(j) && i!=j){
            ans = 1+ LRSUtil(dp,i+1,j+1,s);
        }else{
            ans = Math.max(LRSUtil(dp,i+1,j,s),LRSUtil(dp,i,j+1,s));
        }
        dp[i][j] = ans;

        return dp[i][j];
    }

    public static int  LRS(String s){
        int[][] dp = new int[s.length()+1][s.length()+1];
        for(int i=0;i<=s.length();i++){
            Arrays.fill(dp[i],-1);
        }
        return  LRSUtil(dp,0,0,s);
    }

    public static void main(String[] args){
        System.out.println(LRS("abcbdcd"));
    }
}
