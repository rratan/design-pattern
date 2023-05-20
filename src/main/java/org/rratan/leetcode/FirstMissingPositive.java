package org.rratan.leetcode;

public class FirstMissingPositive {
    public static    int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i , j;
        // int isZero = 0;
        for(i=0;i<n;i++){
            if(nums[i]<0 || nums[i]>n){
                nums[i]=0;
            }
        }
        for(i=0;i<n;i++){
            if(nums[i]==0){
                continue;
            }
            j = nums[i];
            if(Math.abs(j)>n){
                continue;
            }
            if(j<0){
                j = -1*j;
            }
            nums[j-1] = nums[j-1]==0?-1*(n+1):(nums[j-1]<0?nums[j-1]:-1*nums[j-1]);
        }
        for(i=0;i<n;i++){
            if(nums[i]>=0){
                return i+1;
            }
        }
        return n+1;

    }
}
