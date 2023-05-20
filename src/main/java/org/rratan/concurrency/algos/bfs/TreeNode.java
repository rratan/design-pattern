package org.rratan.concurrency.algos.bfs;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements BaseNode {
    private List<TreeNode> pointer;
    private int val;

    public TreeNode(int val){
        pointer = new ArrayList<>();
        this.val = val;
    }

    public void add(TreeNode node){
        pointer.add(node);
    }

    @Override
    public int getVal() {
        return val;
    }

    @Override
    public List<TreeNode> getPointer() {
        return pointer;
    }
}
