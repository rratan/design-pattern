package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MagicDictionary {
    public static void main(String[] args) throws InterruptedException{
//        Arrays.sort(new int[],new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return 0;
//            }
//
//
//        });
        char s;
        Trie2_0 tt = new Trie2_0();

        Long b = System.currentTimeMillis()/1000;
        System.out.println((char)((int)'a'+1));
        Thread.sleep(10000);
        Long a = System.currentTimeMillis()/1000;
        System.out.println( a-b);
        String[] dict = {"abc","cde","efg"};
        for(String el:dict){
            tt.insert(el);
        }
        List<Integer> ll = new ArrayList<>();

        System.out.println(tt.find("cde"));
        System.out.println(tt.find(""));
    }
}



class Trie{
    private TrieNode root = new TrieNode();


    public void insert(String s){
        TrieNode temp = this.root;
        for(int i=0;i<s.length();i++){
            if(temp.getNext(s.charAt(i))==null){
                temp.update(s.charAt(i));
            }
            temp = temp.getNext(s.charAt(i));

            if(i==s.length()-1){
                temp.setWordEnd();
            }

        }
    }

    public boolean find(String s){
        TrieNode temp = this.root;
        for(int i=0;i<s.length();i++){
            if(temp.getNext(s.charAt(i))==null){
                return false;
            }

            temp = temp.getNext(s.charAt(i));

        }
        return temp.isWordEnd();
    }
}


class TrieNode{
    TrieNode[] node =new TrieNode[26];
    private boolean wordEnd = false;

    void TrieNode(){
        for(int i=0;i<26;i++){
            node[i]=null;
        }
    }

    public void update(char ch){
        node[getAscii(ch)] = new TrieNode();
    }

    public TrieNode getNext(char ch){
        return node[getAscii(ch)];
    }

    public boolean isWordEnd(){
            return wordEnd;
    }

    public void setWordEnd(){
        this.wordEnd = true;
    }

    public static int getAscii(char ch){
        return ch-'a';
    }
}



class Trie2_0{
    public Trie2_0[] next;
    private static final Trie2_0 root = new Trie2_0();
    public boolean isWordEnd = false;

    public Trie2_0(){
        this.next = new Trie2_0[26];
        for(int i =0;i<26;i++){
            this.next[i] = null;
        }

    }

    public int getInt(char ch){
        return ch-'a';
    }

    public boolean find(String s){
        int i=0,n=s.length(),j;
        Trie2_0 currNode = Trie2_0.root;
        while(i < n){
            j = getInt(s.charAt(i));
            if(currNode.next[j] == null){
//                System.out.println("No"+s.charAt(i));
                return false;
            }
            currNode = currNode.next[j];
            i++;
        }
        return currNode.isWordEnd;
    }

    public void insert(String s){
        int i=0,n=s.length(),j;
        Trie2_0 currNode = Trie2_0.root;
        while(i < n){
            j = getInt(s.charAt(i));
            if(currNode.next[j] == null){
                currNode.next[j] = new Trie2_0();
            }
            currNode = currNode.next[j];
            i++;
        }
        currNode.isWordEnd = true;
        return;
    }

}
