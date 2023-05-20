package org.rratan.leetcode;

import java.util.HashMap;

public class _659_SplitArray {
        public boolean isPossible(int[] nums) {
            HashMap<Integer,Integer> freq = new HashMap<>();
            HashMap<Integer,Integer> req = new HashMap<>();

            int n = nums.length,i=0;

            for(i=0;i<n;i++){
                freq.put(nums[i],freq.getOrDefault(nums[i],0)+1);
            }

            for(i=0;i<n;i++){
                if(freq.get(nums[i])==0){
                    continue;
                }

                if(req.getOrDefault(nums[i],0)>0){
                    req.put(nums[i],req.get(nums[i])-1);
                    req.put(nums[i]+1,req.getOrDefault(nums[i]+1,0)+1);
                }else if(freq.getOrDefault(nums[i]+1,0)>0 && freq.getOrDefault(nums[i]+2,0)>0){
                    freq.put(nums[i]+1,freq.get(nums[i]+1)-1);
                    freq.put(nums[i]+2,freq.get(nums[i]+2)-1);
                    req.put(nums[i]+3,req.getOrDefault(nums[i]+3,0)+1);
                }else{
                    return false;
                }
                freq.put(nums[i],freq.get(nums[i])-1);
            }
            return true;
        }
}
