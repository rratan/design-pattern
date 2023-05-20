package org.rratan.consistentHashing.impl;

import org.rratan.consistentHashing.skeleton.HashFunction;
import org.rratan.consistentHashing.skeleton.Node;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashRing<T extends Node> {
    private final SortedMap<String ,VirtualNode<T >> ring = new TreeMap<>();
    private final HashFunction hash;

    public ConsistentHashRing(HashFunction hash){
        this.hash = hash;
    }

    public void add(T node,int replicas){
        int replicaCont = getExistingReplica(node);
        for(int i=1;i<=replicas;i++){
            VirtualNode temp = new VirtualNode(node,replicaCont+1);
            ring.put(hash.getHash(temp.getKey()),temp);
        }

    }

    public int getExistingReplica(T node){
        int replicas = 0;
        for(VirtualNode<T> p:ring.values()){
            if(p.isEqual(node)){
                replicas++;
            }
        }
        return replicas;
    }

    public void remove(T node){
        Iterator<String > it = ring.keySet().iterator();

        while (it.hasNext()){
            String key = it.next();
            if(ring.get(key).isEqual(node)){
                it.remove();
            }
        }
    }

    public T routeNode(String key){
        if(ring.isEmpty()){
            return null;
        }
        String keyHash = hash.getHash(key);
        SortedMap<String ,VirtualNode<T >> tailMap = ring.tailMap(keyHash);
        String nodeHash = tailMap.isEmpty()?ring.firstKey():tailMap.lastKey();
        return ring.get(nodeHash).getPhysicalNode();
    }

}
