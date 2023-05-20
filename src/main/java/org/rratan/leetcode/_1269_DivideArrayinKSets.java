package org.rratan.leetcode;

import java.util.HashMap;
import  java.util.Arrays;

public class _1269_DivideArrayinKSets {
        public boolean isPossibleDivide(int[] nums, int k) {
            if (nums == null || nums.length < k) {
                return false;
            }
            Arrays.sort(nums);
            int j=0;
            HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
            for (int num : nums) {
                freq.put(num,freq.getOrDefault(num,0)+1);
            }

            for (int num : nums) {
                if(freq.get(num)==0){
                    continue;
                }
                for(j=0;j<k;j++){
                    if(freq.getOrDefault(num+j,0)==0){
                        return false;
                    }
                    freq.put((num+j),freq.get(num+j)-1);
                }
            }
            return true;

        }
}
