package org.rratan.leetcode;

import java.util.*;

public class _503_NextGreaterElement {

    public void findNextMinimum(int[] nums,int i,int inc,int [] ans,int n){
        Stack<Integer> st = new Stack<>();
        while(i< n && i>=0){
            while(!st.isEmpty() && st.peek()< nums[i]){
                st.pop();
            }
            if(st.isEmpty()){
                ans[i] = Math.max(ans[i],-1);
            }else{
                if(ans[i]==-1){
                    ans[i] = st.peek();
                }
            }
            st.add(nums[i]);
            i+=inc;
        }
    }

    public void finFarMinimum(int[] nums,int i,int inc,int [] ans,int n){
        Stack<Integer> st = new Stack<>();
        Stack<Integer> st1 = new Stack<>();
        while(i<n && i>=0){
            while(!st.isEmpty() &&  st.peek() >= nums[i]){
                st1.add(st.pop());
            }
            if(!st1.isEmpty()){
                if(ans[i]==-1){
                    ans[i]=st1.peek();
                }
            }
            while(!st1.isEmpty()){
                st.add(st1.pop());
            }
            if(st.isEmpty() || st.peek() < nums[i]){
                st.add(nums[i]);
            }
            i+=inc;
        }
    }
    public int[] nextGreaterElements(int[] nums) {
        int n  = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans,-1);
        findNextMinimum(nums,n-1,-1,ans,n);
        finFarMinimum(nums,0,1,ans,n);
        return ans;
    }
}
