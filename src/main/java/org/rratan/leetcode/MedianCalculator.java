package org.rratan.leetcode;

import java.util.PriorityQueue;

class MedianCalculator {
    /** initialize your data structure here. */

    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;
    private boolean isEven = true;

    public MedianCalculator() {
        left = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if(left.isEmpty()){
            left.offer(num);
            isEven = false;
            return;
        }

        if(right.isEmpty()){
            left.offer(num);
            isEven = true;
            return;

        }

        if(isEven){
            if(left.peek()> num){
                left.offer(num);
            }else{
                left.offer(right.poll());
                right.offer(num);
            }

        }else{
            if(left.peek()<= num){
                right.offer(num);
            }else{
                right.offer(left.poll());
                left.offer(num);
            }
        }
        isEven = !isEven;
    }

    public float getMedian() {
        if(isEven){
            return (left.peek() + right.peek())/2.0f;
        }
        return left.peek()*1.0f;
    }
}