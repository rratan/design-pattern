package org.rratan.leetcode;

import java.util.HashMap;

public class PartitionSubStrinngWithK {
    public int charToInt(char ch){
        return ch-'0';
    }
    public int minimumPartition(String s, int k) {
        int i,j,n,l,m;
        long val;
        n = s.length();
        i=0;

        i=0;
        int ans=0;
        while(i<n){

            val = (long)charToInt(s.charAt(i));
            if(val>k){

                return -1;
            }
            j=i+1;
            while(j<n){
                l = charToInt(s.charAt(j));

                val = val*10+(long)l;
                if(val> (long)k){
                    break;
                }
                j++;
            }
            ans++;
            i=j;
        }
        return ans;

    }
}
