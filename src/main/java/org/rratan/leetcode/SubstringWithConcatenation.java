package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringWithConcatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String,Integer> mp = new HashMap<>();
        HashMap<String,Integer> mp1;
        for(String el:words){
            mp.put(el,mp.getOrDefault(el,0)+1);
        }
        int i,j,k,l,n,m;
        n = s.length();
        k = words.length;
        m = words[0].length();
        List<Integer> ans = new ArrayList<>();
        i=0;
        j=0;
        while(i<n){
            mp1 = new HashMap<>(mp);
            j = i;
            l = k;
            while(j<n){
                String sub = s.substring(j,j+m);
                if(mp1.getOrDefault(sub,0)>0){
                    l--;
                    mp1.put(sub,mp1.get(sub)-1);
                }else{
                    break;
                }
                if(l==0){
                    ans.add(i);
                    break;
                }
                j=j+m;

            }
            i++;
        }
        return ans;
    }

}
