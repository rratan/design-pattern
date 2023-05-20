package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _60_permutation_sequence {
    public void cacheFactorial(int[] ans,int n){
        int pre=1;
        ans[0]=1;
        for(int i=1;i<=n;i++){
            ans[i] = ans[i-1]*i;
        }
    }

    public int getNum(HashMap<Integer,Integer> mp,int n,int m){
        for(int i=1;i<=m;i++){
            if(mp.containsKey(i)){
                continue;
            }
            n--;
            if(n==0){
                return i;
            }
        }
        return -1;
    }

    public String getPermutation(int n, int k) {
        int[] fact = new int[n+1];
        cacheFactorial(fact,n);
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer,Integer> mp = new HashMap<>();
        int i,l,m,a=n;

        while(a>0){
            l = (int)Math.ceil((double)k / (double)fact[a-1]);
            m = getNum(mp,l,n);
            ans.add(m);
            mp.put(m,1);
            k = k- fact[a-1]*(l-1);
            a--;
        }
        StringBuilder sb = new StringBuilder();
        for(i=0;i<ans.size();i++){
            sb.append(Integer.toString(ans.get(i)));
        }
        return sb.toString();
    }
}
