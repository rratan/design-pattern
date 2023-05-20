package org.rratan.leetcode;


import org.rratan.concurrency.App;

import java.util.Stack;

public class MinStack {

}

class Approach_2{
    private Stack<Integer> st = new Stack<>();
    private int minTop = Integer.MAX_VALUE;

    public void push(int val) {
        if(st.isEmpty()){
            st.push(val);
            minTop = val;
            return;
        }
        if(val < minTop){
            //Store the previous min
            int smartVal = 2*val - minTop;
            minTop = val;
            st.push(smartVal);
            return;
        }
        st.push(val);
    }

    public void pop() {
        if(st.isEmpty()){
            return;
        }
        int val  = st.pop();
        if(val < minTop){
            minTop = 2*minTop  - val; //(2*minTop-(2*minTop-prevMin)) = prevMin
        }
    }

    public int top() {
        if(st.isEmpty()){
            return -1;
        }
        int val = st.peek();
        if(val < minTop){
            return minTop; // top value is same as minTop. val contains smartVal
        }
        return val;
    }

    public int getMin() {
        return  minTop;
    }
}


class Approach_1 {
    public static class Mypair{
        private int val,minVal;
        public Mypair(int val){
            this.val = val;
        }
        public void setMinVal(int val){
            this.minVal = minVal;
        }

        public int getVal(){
            return this.val;
        }
        public int getMinVal(){
            return this.minVal;
        }
    }
    private Stack<Mypair> st = new Stack<>();
    private int minTop = Integer.MAX_VALUE;
    public Approach_1(){

    }
    public void push(int val) {
        Mypair pushReady = new Mypair(val);
        minTop = Math.max(val, minTop);
        pushReady.setMinVal(minTop);
        st.push(pushReady);
    }

    public void pop() {
        if(st.isEmpty()){
            return;
        }
        Mypair topPair = st.pop();
        if(st.isEmpty()){
            minTop = Integer.MAX_VALUE;
        }else{
            minTop = st.peek().getMinVal();
        }

    }

    public int top() {
        if(st.isEmpty()){
            return -1;
        }
        return  st.peek().getVal();
    }

    public int getMin() {
            return minTop;
    }
}
