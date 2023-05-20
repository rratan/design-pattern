package org.rratan.leetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class _61_rotateList {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode slow,fast,curr;
        curr = head;
        int len=0,j;
        while(curr!=null){
            curr = curr.next;
            len++;
        }
        if(len==0){
            return head;
        }
        k = k%len;
        if(k==0){
            return head;
        }
        k = len-k-1;
        j=0;
        fast = head;
        slow = head;
        while(j<k){
            fast = fast.next;
            j++;
        }
        head = fast.next;
        fast.next = null;
        fast = head;
        while(fast.next!=null){
            fast = fast.next;
        }
        fast.next = slow;

        return head;

    }
}
