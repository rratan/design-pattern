package org.rratan.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _239_slidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new LinkedList<>();
        int i,j,n,m;
        n = nums.length;
        m = n-k+1;
        int[] ans = new int[m];
        i=0;
        j=0;
        while(i<k){
            while(!dq.isEmpty() && nums[i]>nums[dq.peekLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
            i++;
        }
        while(i<n){
            ans[j] = nums[dq.peekFirst()];
            j++;
            while(!dq.isEmpty() && dq.peekFirst()<=i-k)
            {dq.removeFirst();}
            while(!dq.isEmpty() && nums[i]>nums[dq.peekLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
            i++;
        }
        if(!dq.isEmpty()){
            ans[j] = nums[dq.peekFirst()];
            j++;
        }

        return ans;
    }
}
