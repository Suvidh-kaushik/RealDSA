package javaCode;

// Graphs - Traversals

/* 1) Once a Graph is created i.e represented in the form of an adjacency list or matrix, the next step which
* comes in mind is how do we move through a graph where do we start
* 2) For trees its simple, we start at the root node and move accordingly if we want to go down the depth first
* or explore the tree level wise
* 3) Similarly in Graphs also we have 2 types of traversals, Breadth first traversal and a Depth first traversals
* 4)  Breadth first traversal - In this traversal we begin from the startNode and move level wise first i.e the nodes directly adjacent
* to the startNode are printed first then the nodes of the next level and so on
*        T.C - O(V+E)
*        S.C - O(V) -> due to queue
*     Depth first traversal - In this traversal also we begin from the startNode but we move depth-wise first
* and after reaching the end of first node we move to the next node and iterate till its end, generally we use a backtracking based
* approach for DFS
*        T.C - O(V+E)
*        S.C - O(V) -> due to recursion stack space
*
*  Why O(V+E)?
*  - Since in both the traversals what we are doing is going through a vertex and travelling through each edge once
*
*
*
*    Ex-     1 - 2 - 5
*            |   |
*            3 - 4
*
*       startNode = 1
*        BFS - 1,2,3,5,4
*        DFS - 1,2,5,4,3
*
*
* */

import java.util.*;

class BFS<T>{
    private Map<T,List<T>> adjL;
    private Map<T,Boolean> vis;
    private T startNode;

    BFS(T sN,Map<T,List<T>>adjL){
        this.adjL = adjL;
        this.vis = new HashMap<>();
        this.startNode = sN;
    }

    public void markVisited(T node){
        vis.put(node,true);
    }

    public boolean checkVisited(T node){
        if(vis.containsKey(node)){
            return true;
        }
        return false;
    }

    public void printBFS(){
         Queue<T> q = new LinkedList<>();
         markVisited(startNode);
         q.add(startNode);
         while(!q.isEmpty()){
             T curr = q.poll();
             System.out.print(curr+"  ");
             for(T itr:adjL.get(curr)){
                 if(!checkVisited(itr)){
                     markVisited(itr);
                     q.add(itr);
                 }
             }
         }
      System.out.println();
    }
}


class DFS<T>{
    private Map<T,List<T>> adjL;
    private Map<T,Boolean> vis;
    private T startNode;

    DFS(T sN,Map<T,List<T>>adjL){
        this.startNode = sN;
        vis = new HashMap<>();
        this.adjL = adjL;
    }

    public void markVisited(T node){
        vis.put(node,true);
    }

    public boolean checkVisited(T node){
        return vis.containsKey(node);
    }

    public void recurse(T N){
       markVisited(N);
       System.out.print(N+"  ");
       for(T itr:adjL.get(N)){
           if(!checkVisited(itr)){
               recurse(itr);
           }
       }
    }

    public void printDFS(){
        recurse(startNode);
        System.out.println();
    }
}


public class Traversals {
    public static void main(String [] args){
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addUndirectedEdge(1,2);
        graph.addUndirectedEdge(1,3);
        graph.addUndirectedEdge(2,4);
        graph.addUndirectedEdge(2,5);
        graph.addUndirectedEdge(3,4);

        graph.printGraph();

        BFS<Integer> bfs_traversal = new BFS<>(1,graph.getAdjL());
        DFS<Integer> dfs_traversal = new DFS<>(1,graph.getAdjL());

        System.out.print("BFS-->");
        bfs_traversal.printBFS();

        System.out.print("DFS-->");
        dfs_traversal.printDFS();
    }
}
