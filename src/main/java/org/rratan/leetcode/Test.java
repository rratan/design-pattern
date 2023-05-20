package org.rratan.leetcode;

import java.util.HashMap;
class  Mypair{
    int x = 0;
    int  y =0;

}

class StringPair implements Comparable<StringPair> {
    String s;
    int cnt;
    public StringPair(String s,int c){
        this.s = s;
        this.cnt = c;
    }


    @Override
    public int compareTo(StringPair o) {
        return 0;
    }
}
public class Test {

    public static void main(String[] args){
        HashMap<String, Mypair>  mp = new HashMap<>();

        mp.put("12",new Mypair());

        Mypair temp = mp.get("12");
        System.out.println(temp.x);
        temp.x+=1;
         temp = mp.get("12");
        System.out.println(temp.x);


    }
}
