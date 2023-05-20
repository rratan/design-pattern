package org.rratan.leetcode;

import sun.net.www.http.HttpClient;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MedianOfSortedArray {
    public static double findMedianUtil(int[] nums1,int[] nums2,int n,int m){
        int lsum = n+m;
        int left = (lsum+1)/2;
        int i,j,a1,a2,b1,b2,mid1,l,r,mid2;
        double ans=0.0;
        l = 0;
        r = n;
        while(l<=r){
            i = (l+r)/2;
            j = left - i ;
//            System.out.println(i+"::"+j);
            a1 = i>0?nums1[i-1]:Integer.MIN_VALUE;
            a2 = i<n?nums1[i]:Integer.MAX_VALUE;
            b1 = j>0?nums2[j-1]:Integer.MIN_VALUE;
            b2 = j<m?nums2[j]:Integer.MAX_VALUE;
//            System.out.println(a1+" "+a2+" "+b1+" "+b2);
            if(a2>=b1 && b2>=a1){
                if(lsum%2==0){
                    ans = (Math.max(a1,b1)+Math.min(a2,b2))/2.0;
                }else{
                    ans = Math.min(b2,a2);
                }
                return ans;
            }
            else if(b2<a1){
                r = i-1;
            }
            else{
                l=i+1;
            }
        }
        return ans;

    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n,m;
        n = nums1.length;
        m = nums2.length;
        if(n>m){
            return findMedianUtil(nums2,nums1,m,n);
        }
        return findMedianUtil(nums1,nums2,n,m);

    }

    public static void  main(String [] args){
        System.out.println(findMedianSortedArrays(new int[]{2,2,4,5},new int[]{10,10,10}));
    }
}
