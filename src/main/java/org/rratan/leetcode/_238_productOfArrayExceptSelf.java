package org.rratan.leetcode;

public class _238_productOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int left = 1;
        int n = nums.length, i ,j;
        int[] rightP = new int[n];
        rightP[n-1]=1;
        i=n-2;
        while(i>=0){
            rightP[i]=rightP[i+1]*nums[i+1];
            i--;

        }
        i=0;
        while(i<n){
            rightP[i] = rightP[i]*left;
            left = left*nums[i];
            i++;
        }

        return rightP;

    }
}
