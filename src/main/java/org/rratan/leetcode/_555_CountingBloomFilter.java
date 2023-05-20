package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _555_CountingBloomFilter {

    private class HashFunction{
        private  int seed;
        private int cap;

        public HashFunction(int seed, int cap){
            this.seed = seed;
            this.cap = cap;
        }

        public int getHash(String s){
            int res = 0;
            for(int i=0;i<s.length();i++){
                res += res*seed + s.charAt(i);
                res %= cap;
            }
            return res;
        }
    }
    /*
     * @param k: An integer
     */
    private int[] bits;
    private int bSize;
    List<HashFunction> hash = new ArrayList<>();
    public _555_CountingBloomFilter(int k) {
        this.bSize = k;
        for(int i=0;i<k;i++){
            hash.add(new HashFunction(2*i+3,10000+i));
        }
        bits = new int[10000 + k];

    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        // write your code here
        for(int i=0;i<bSize;i++){
            bits[hash.get(i).getHash(word)]+=1;
        }
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void remove(String word) {
        // write your code here
        for(int i=0;i<bSize;i++){
            bits[hash.get(i).getHash(word)]-=1;
        }
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {
        // write your code here
        for(int i=0;i<bSize;i++){
            if(bits[hash.get(i).getHash(word)] <=0){
                return false;
            }
        }
        return true;
    }
}
