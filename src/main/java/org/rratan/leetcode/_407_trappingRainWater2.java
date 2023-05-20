package org.rratan.leetcode;

import javax.print.attribute.standard.PrinterResolution;
import java.util.PriorityQueue;

public class _407_trappingRainWater2 {

    public class MyPair implements Comparable<MyPair>{
        public int height,x,y;
        public MyPair(int height,int x,int y){
            this.height =height;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(MyPair o) {
            return this.height-o.height;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<MyPair> pq = new PriorityQueue<>();
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        int n,m,i,j,ans=0,k;
        n = heightMap.length;
        m = heightMap[0].length;
        boolean[][] bl =new boolean[n][m];
        for(i=0;i<n;i++){
            pq.offer(new MyPair(heightMap[i][0],i,0));
            pq.offer(new MyPair(heightMap[i][m-1],i,m-1));
            bl[i][0]=true;
            bl[i][m-1]=true;
        }
        for(i=0;i<m;i++){
            pq.offer(new MyPair(heightMap[0][i],0,i));
            pq.offer(new MyPair(heightMap[n-1][i],n-1,i));
            bl[0][i]=true;
            bl[n-1][i]=true;
        }

        while(!pq.isEmpty()){
            MyPair temp = pq.poll();
            int x = temp.x;
            int y = temp.y;
            for(k=0;k<4;k++){
                if(x+dir[k][0]<n-1 && x+dir[k][0]>0 && y+dir[k][1]<m-1 && y+dir[k][1]>0){
                    if(!bl[x+dir[k][0]][y+dir[k][1]]){
                        ans += temp.height>=heightMap[x+dir[k][0]][y+dir[k][1]]?temp.height-heightMap[x+dir[k][0]][y+dir[k][1]]:0;
                        pq.offer(new MyPair(Math.max(heightMap[x+dir[k][0]][y+dir[k][1]],temp.height),x+dir[k][0],y+dir[k][1]));
                        bl[x+dir[k][0]][y+dir[k][1]] = true;
                    }

                }
            }

        }

        return ans;
    }
}
