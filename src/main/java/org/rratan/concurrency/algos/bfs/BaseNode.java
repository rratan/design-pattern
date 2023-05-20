package org.rratan.concurrency.algos.bfs;

import java.util.List;

public interface BaseNode<T extends  BaseNode> {
    public int getVal();
    public List<T> getPointer();
}
