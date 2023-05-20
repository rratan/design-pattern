package org.rratan.leetcode;

public class _55_jumpGame {
    public boolean canJump(int[] nums) {
        int lastIndex = 0;

        int i=0,n = nums.length;
        if(n<=1){
            return true;
        }
        while(i<n){
            lastIndex = Math.max(lastIndex,i+nums[i]);
            if(lastIndex>=n-1){
                return true;
            }
            if(i==lastIndex){
                if(nums[i]==0){
                    return false;
                }
            }
            i++;
        }


        return false;
    }
}
