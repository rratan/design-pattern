package org.rratan.leetcode;

import java.util.Arrays;

public class _44_wildcardMatching {
    public int cal(int[][] dp,int n,int m,int i,int j,String s, String p){
        if(i>=n && j>=m){
            return 1;
        }
        if(j>=m){
            return -1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int ans=0;
        if(p.charAt(j)=='*'){
            int temp = i;
            ans = cal(dp,n,m,i,j+1,s,p);
            while(temp<n){
                temp++;
                ans = Math.max(ans,cal(dp,n,m,temp,j+1,s,p));
            }
        }else{
            if(i>=n || (s.charAt(i)!=p.charAt(j) && p.charAt(j)!='?')){
                ans = 0;
            }
            else{
               ans = cal(dp,n,m,i+1,j+1,s,p);
            }
        }
        dp[i][j] = ans;
        return ans;
    }

    public boolean isMatch(String s, String p) {
        int n,m,i,j;
        n = s.length();
        m = p.length();
        int[][] dp = new int[n+1][m];
        for(i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }

        j = cal(dp,n,m,0,0,s,p);
        return j==1?true:false;

    }
}
