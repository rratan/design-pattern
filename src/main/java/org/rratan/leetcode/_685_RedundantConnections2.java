package org.rratan.leetcode;

import java.util.HashMap;

public class _685_RedundantConnections2 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
            int n = edges.length, m=0,skip;
            int[] parent = new int[n+1];
            int[] ed1 = new int[]{-1,-1};
            int[] ed2 = new int[]{-1,-1};

            for(int i=0;i<n;i++ ){
                int[] edge = edges[i];
                if(parent[edge[1]]==0){
                    parent[edge[1]] = edge[0];
                }else{
                    ed1 = new int[]{edge[0],edge[1]};
                    ed2 = new int[]{parent[edge[1]],edge[1]};
                    edge[1] = 0;
                    edges[i]=edge;
                    break;
                }
            }
            for(int i=0;i<=n;i++){
                parent[i]=i;
            }

            for(int i=0;i<n;i++){
                int[] edge = edges[i];
                if(edge[1]==0){
                    continue;
                }
                if(find(edge[0],parent )==edge[1]){
                    if (ed2[0] == -1) {
                        return edge;
                    }
                    return ed2;
                }
                parent[edge[1]] = edge[0];
            }
            return ed1;
    }

    public int find(int x,int[] parent){
        if(x!=parent[x]){
            parent[x] = find(parent[x],parent);
        }
        return parent[x];
    }

}
