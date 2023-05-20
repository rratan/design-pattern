package org.rratan.leetcode;

import java.util.Stack;

public class _42_tappingRainWater {
    public int trap(int[] height) {

        int n = height.length;
        int l=0,r=n-1,lmax=0,rmax=0;

        int ans=0;
        while(l<=r){
            if(height[l]<height[r]){
                if(lmax<height[l]){
                    lmax=height[l];
                }
                else{
                    ans+=lmax-height[l];
                }
                l++;
            }else{
                if(rmax<height[r]){
                    rmax=height[r];
                }
                else{
                    ans+=rmax-height[r];
                }
                r--;
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        Stack<Integer> st = new Stack<>();
        int n = height.length;
        int i,j,ans=0;
        i=0;
        while(i<n){
            while(!st.isEmpty() && height[i]>=height[st.peek()]){
                j = st.peek();
                st.pop();
                if(st.isEmpty()){
                    break;
                }
                ans+=(Math.min(height[st.peek()],height[i])-height[j])*(i-st.peek()-1);

            }
            st.push(i);
            i++;
        }
        return ans;
    }
}
