package org.rratan.leetcode;

public class FloydWarshalAlgo {
    private int[][] allPairCost;
    private int[][] graphWeight;
    private int verticesSize;

    public FloydWarshalAlgo(int verticesSize,int[][] graph){
        this.verticesSize = verticesSize;
        this.graphWeight = graph;
    }

    public void findAllPairShortestPath(){

        for(int k=0;k<verticesSize;k++){
            for(int i=0;i<verticesSize;i++){
                for(int j=0;j<verticesSize;j++){
                    graphWeight[i][j]=Math.min(graphWeight[i][j],graphWeight[i][k]+graphWeight[k][j]);
                }
            }
        }
    }

    public void  printAllPairShortestPath(){
        for(int i=0;i<verticesSize;i++){
            System.out.println("Shortest path distance from vertice"+ i);
            for(int j=0;j<verticesSize;j++){
                System.out.println("to  vertice"+ j+" : "+graphWeight[i][j]);
            }
        }
    }

    public static void main(String[] args){
        int inf = 2000;
        int[][] graph = {
                {0,1,2,inf,inf},
                {1,0,3,inf,inf},
                {inf,2,0,4,inf},
                {inf,inf,4,0,5},
                {2,inf,inf,5,0}
        };
        FloydWarshalAlgo fp = new FloydWarshalAlgo(5,graph);
        fp.findAllPairShortestPath();
        fp.printAllPairShortestPath();
    }
}
