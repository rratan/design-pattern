package org.rratan.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _737_SentenceSimilarity {
    public static boolean checkAns(List<List<Integer>> graph, int source,int dest,boolean[] vis){
            if(source==dest){
                return true;
            }
            boolean ans = false;
            for(Integer el: graph.get(source)){
                if(!vis[el]){
                    vis[el]  = true;
                    ans = ans | checkAns(graph,el,dest,vis);
                }
            }
            return ans;
    }

    public static int find(int node,int[] parent){
        if(parent[node]==node){
            return node;
        }
        parent[node] = find(parent[node],parent);
        return parent[node];
    }

    public static void union(int x,int y,int[] parent){
        parent[find(x,parent)] = find(y,parent);
        return;
    }

    public static boolean areSentencesSimilarTwo(List<String> words1, List<String> words2, List<List<String>> pairs) {
        if(words1.size()!=words2.size()){
            return false;
        }
        HashMap<String,Integer> mp = new HashMap<>();

        List<Integer> temp;
        int l=0, x , y,i,j;
        for(List<String> el:pairs){
            if(!mp.containsKey(el.get(0))){
                mp.put(el.get(0),l);
                l++;
            }
            if(!mp.containsKey(el.get(1))){
                mp.put(el.get(1),l);
                l++;
            }


        }
        int[] parent = new int[l];
        for(i=0;i<l;i++){
            parent[i]=i;
        }
        for(List<String> el:pairs){
            x = mp.get(el.get(0));
            y = mp.get(el.get(1));
            union(x,y,parent);
        }

        int n = words1.size();
        for(i=0;i<n;i++){
            if(words1.get(i).equals(words2.get(i))){
                return true;
            }
            if(!mp.containsKey(words1.get(i)) || !mp.containsKey(words2.get(i))){
                return false;
            }
            x = mp.get(words1.get(i));
            y = mp.get(words2.get(i));
            if(x==y){
                continue;
            }
           if(find(x,parent)!=find(y,parent)){
               return false;
           }
        }

        return true;

    }
    public static void main(String[] args){
        String[] words1 = new String[]{"great", "acting", "skills"};
        String[] words2 = new String[]{"fine", "drama", "talent"};
        List<List<String>> paris = new ArrayList<>();
        paris.add(new ArrayList<>(Arrays.asList("great","good")));
        paris.add(new ArrayList<>(Arrays.asList("fine","good")));
//        paris.add(new ArrayList<>(Arrays.asList("acting","drama")));
        paris.add(new ArrayList<>(Arrays.asList("skills","talent")));
//        System.out.print(areSentencesSimilarTwo(words1,words2,paris));
    }

}
