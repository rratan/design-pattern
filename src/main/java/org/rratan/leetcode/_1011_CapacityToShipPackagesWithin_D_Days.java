package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class _1011_CapacityToShipPackagesWithin_D_Days {
    public int shipWithinDays(int[] weights, int days) {
        int lo=0,hi=0;
        int i,j,n,m,lsum;
        n = weights.length;
        for(Integer el: weights){
            hi+=el;
            lo = Math.max(lo,el);
        }
        int ans = hi;
        while(lo<=hi){
            int mid = lo +(hi-lo)/2;
            j = 0;
            i = 0;
            lsum=0;
            while(i<n){
                lsum+=weights[i];
                if(lsum==mid){
                    j++;
                    lsum = 0;
                }else if(lsum>mid){
                    j++;
                    lsum = weights[i];
                }
                i++;
            }
            if(lsum>0){
                j++;
            }
            if(j<=days){
                ans = Math.min(ans,mid);
                hi = mid-1;
            }
            else{
                lo = mid+1;
            }
        }
        return ans;
    }
}
