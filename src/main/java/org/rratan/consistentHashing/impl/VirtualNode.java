package org.rratan.consistentHashing.impl;

import org.rratan.consistentHashing.skeleton.Node;

public class VirtualNode<T extends Node> implements Node{
    private T physicalNode;
    private int replicaUID;

    public VirtualNode(T physicalNode,int replicaId){
        this.physicalNode = physicalNode;
        this.replicaUID = replicaId;
    }
    @Override
    public String getKey(){
        return physicalNode.getKey()+"_"+replicaUID;
    }

    public int getReplicaUID(){
        return this.replicaUID;
    }
    public T getPhysicalNode(){
        return this.physicalNode;
    }

    public boolean isEqual(T node){
        return this.physicalNode.getKey().equals(node.getKey());
    }


}
