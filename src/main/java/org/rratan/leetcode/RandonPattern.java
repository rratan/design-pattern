package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RandonPattern {
    public static ArrayList<ArrayList<Integer>> printMagicalPattern(int n) {
        // Wrtie your code here.

        int dupCnt = 1;
        int dupEl = n;
        int i = 1;

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<ArrayList<Integer>> fans = new ArrayList<>();
        while(n > 0){
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<Integer> row = new ArrayList<>();

            while( dupCnt > 0 ){
                temp.add(dupEl);
                dupCnt--;
            }

            int el = dupEl-1;
            while(el > 0){
                temp.add(el);
                el--;
            }
            row.addAll(temp);
            for(int j = temp.size()-2;j>=0;j--){
                row.add(temp.get(j));
            }

            ans.add(row);
            n--;
            i++;
            dupEl = n;
            dupCnt = i;
        }
        fans.addAll(ans);
        for(i= ans.size()-2;i>=0;i--){
            fans.add(ans.get(i));
        }

        return fans;

    }

    public static void main(String[] args){
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(Integer el : mp.keySet()){

        }
        for(ArrayList<Integer> el: printMagicalPattern(6)){
            for(Integer j: el){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
