package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _315_CountOfSmallerNumbers {

     int getSum(int[] ar,int i){
        int sum=0;
        while(i>0){
            sum+=ar[i];
            i-= (i & -i);
        }
        return sum;
    }

    void update(int[] ar,int i,int ma){
        while(i<=ma){
            ar[i]+=1;
            i+= (i& -i);
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int min = Integer.MAX_VALUE,ma = Integer.MIN_VALUE;
        int n = nums.length,i;
        for(i=0;i<n;i++){
            min = Math.min(nums[i],min);
            ma = Math.max(nums[i],ma);
        }
        min = min<0?-1*min:0;
        min++;
        ma=ma+min;
        for(i=0;i<n;i++){
            nums[i]+=min;
        }
        int[] ar = new int[ma+10];
        List<Integer> ans = new ArrayList<>();
        for(i=n-1;i>=0;i--){
            ans.add(getSum(ar,nums[i]-1));
            update(ar,nums[i],ma);
        }

        Collections.reverse(ans);
        return ans;
    }
}
