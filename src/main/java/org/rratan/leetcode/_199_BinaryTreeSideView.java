package org.rratan.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }

public class _199_BinaryTreeSideView {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> qe = new LinkedList<>();

        List<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        qe.add(root);
        TreeNode curr;
        int layerSize;
        while(!qe.isEmpty()){
            layerSize = qe.size();
            while(layerSize > 0){
                curr = qe.poll();
                if(layerSize == 1){
                    ans.add(curr.val);
                }
                if(curr.left!=null){
                    qe.add(curr.left);
                }
                if(curr.right!=null){
                    qe.add(curr.right);
                }
                layerSize--;
            }
        }
        return ans;
    }
}
