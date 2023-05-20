package org.rratan.leetcode;

import java.util.PriorityQueue;

public class _788_SwinWater {

    public int swimInWater(int[][] grid) {
        int ans = 0;
        int i , j , n = grid.length, m = grid[0].length,t=0,x,y;
        boolean[][] vis = new boolean[n][m];
        PriorityQueue<MyPair> pq = new PriorityQueue<>();
        vis[0][0]=true;
        t = grid[0][0];
        pq.add(new MyPair(0,0,grid[0][0]));

        while(!pq.isEmpty()){
            if(vis[n-1][m-1]){
                break;
            }
            MyPair temp = pq.poll();
            ans += Math.max(0,temp.val-t);
            t = Math.max(t,temp.val);
            int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            for(i =0 ; i< 4;i++){
                x = dir[i][0]+temp.x;
                y = dir[i][1]+temp.y;
                if(x>=0 && x<n && y>=0 && y<m && !vis[x][y]){
                    vis[x][y]=true;
                    pq.add(new MyPair(x,y,grid[x][y]));
                }
            }

        }

        return ans;
    }

    class MyPair implements Comparable<MyPair>{
        int x;
        int y;
        int val;
        public MyPair(int x,int y,int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int compareTo(MyPair obj){
            return this.val - obj.val;
        }
    }
}
