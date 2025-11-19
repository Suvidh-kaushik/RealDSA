package javaCode;
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

public class Traversals {
    public static void main(String [] args){
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addUndirectedEdge(0,1);
        graph.addUndirectedEdge(1,2);
        graph.addUndirectedEdge(0,3);
        graph.addUndirectedEdge(3,4);
        graph.addUndirectedEdge(1,4);

        graph.printGraph();

        BFS<Integer> bfs_traversal = new BFS<>(4,graph.getAdjL());

        bfs_traversal.printBFS();
    }
}
