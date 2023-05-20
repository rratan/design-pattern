package org.rratan.leetcode;

public class ShortestPalindrome {

    public static String bruteForce(String s){
        int i , n = s.length();
        StringBuilder ne = new StringBuilder(s);
        ne.reverse();
        String reverse = ne.toString();
        for(i=0;i<n;i++){
            if(s.substring(0,n-i).equals(reverse.substring(i))){
                return reverse.substring(0,i)+s;
            }
        }
        return "";
    }
     public static int[] constructLCP(String s){
        int n = s.length();
        int i=0,j=0;
        int[] lps = new int[n+1];
        lps[0]=0;
        i=1;
        j=0;
        while(i<n){

            if(s.charAt(i)==s.charAt(j)){
                j++;
                lps[i]=j;
                i++;
                continue;
            }
            if(j!=0){
                j = lps[j-1];
            }else{
                lps[i]=j;
                i++;
            }



        }
        return lps;
     }

    public static String  shortestPalindrome(String s) {
        StringBuilder ne = new StringBuilder(s);
        ne.reverse();
        int n = s.length();
        String reverse = ne.toString();
        int[] lps = constructLCP(s+"#"+reverse);
        int l = lps[lps.length-1];


        return reverse.substring(0,n-l)+s;

    }

    public static void main(String[] args){
        String[] inp = new String[]{"aacecaaa","abcd"};
        for(String in:inp){
            System.out.println(shortestPalindrome(in));
        }
    }
}
