package org.rratan.leetcode;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class MinimumWindowSubString {
    public boolean compareFreArray(int[] a,int[] b){
        for(int i=0;i<256;i++){
            if(a[i]<b[i]){
                return false;
            }
        }
        return true;
    }
    public String minWindow(String s, String t) {
        Deque<Integer> dq= new LinkedList<>();

        int n,m,i,j,k,l,st=-1,ans;
        n = s.length();
        m = t.length();
        ans = Math.max(n+1,m+1);
        int[] freqS = new int[256];
        int[] freQ = new int[256];
        for(i=0;i<m;i++){
            freqS[t.charAt(i)-'A']++;
        }
        i=0;
        j=0;
        while(i<n){
            freQ[s.charAt(i)-'A']++;
            if(compareFreArray(freQ,freqS)){
                while(j<=i && compareFreArray(freQ,freqS)){
                    if(ans>(i-j+1)){
                        st=j;
                        ans = i-j+1;
                    }
                    freQ[s.charAt(j)-'A']--;
                    j++;

                }

            }
            i++;
        }
        if(st==-1){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        i=st;
        while(i<st+ans){
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();


    }
}
