package javaCode;


import java.util.*;


class P<T>{
    T first;
    T second;
    P(T u,T v){
        this.first = u;
        this.second = v;
    }
}


class Undirected_Cycle_Detection<T>{
    private Map<T, List<Pair<T,Integer>>> adjL;
    Map<T,Boolean>vis;

     Undirected_Cycle_Detection(Map<T,List<Pair<T,Integer>>> adjL){
        this.adjL=adjL;
        vis = new HashMap<>();
     }

    boolean checkVisited(T node){
        return vis.containsKey(node);
    }

    void markVisited(T node){
        vis.put(node,true);
    }

    boolean BFS_Checker(T node){
        Queue<P<T>> q = new LinkedList<>();
        q.add(new P<>(node,null));
        markVisited(node);
        while(!q.isEmpty()){
            P<T> temp = q.poll();
            T curr = temp.first;
            T parent = temp.second;
            for(Pair<T,Integer> itr:adjL.get(curr)){
                if(!checkVisited(itr.first)){
                    markVisited(itr.first);
                    q.add(new P<>(itr.first,curr));
                }
                else if(checkVisited(itr.first) && itr.first!=parent) {
                  return true;
                }
            }
        }
       return false;
    }

    boolean BFS_detection(){
         for(T temp:adjL.keySet()){
             if(!checkVisited(temp)){
                 if(BFS_Checker(temp)){
                     return true;
                 }
             }
         }
         return false;
    }

    boolean recursion(T node,T parent){
         markVisited(node);
         for(Pair<T,Integer>itr:adjL.get(node)){
             if(!checkVisited(itr.first)){
                 if(recursion(itr.first, node)){
                     return true;
                 }
             }
             else if(!itr.first.equals(parent)){
                 return true;
             }
         }
         return false;
    }

    boolean DFS_detection(){
         this.vis = new HashMap<>();
         for(T itr: adjL.keySet()){
             if(!checkVisited(itr)){
                 if(recursion(itr,null)){
                     return true;
                 }
             }
         }
         return false;
    }
}



class Directed_Cycle_Detection<T>{
    private Map<T,List<Pair<T,Integer>>>adjL;
    private Map<T,Boolean> vis;

    Directed_Cycle_Detection(Map<T,List<Pair<T,Integer>>>adjL){
        this.adjL = adjL;
        vis = new HashMap<>();
    }

    boolean checkVisited(T node){
        return vis.containsKey(node);
    }

    void markVisited(T node){
        vis.put(node,true);
    }

    List<T> BFS_TopoSort(){
        Map<T,Integer> indegree = new HashMap<>();
      for(T temp: adjL.keySet()) {
          indegree.putIfAbsent(temp,0);
          for (Pair<T, Integer> itr : adjL.get(temp)) {
              T curr = itr.first;
              indegree.put(curr, indegree.getOrDefault(curr, 0) + 1);
          }
      }
       Queue<T> q = new LinkedList<>();
       List<T> res = new ArrayList<>();
       for(T itr: indegree.keySet()){
           System.out.println(itr+"-"+indegree.get(itr));
           if(indegree.get(itr)==0){
               q.add(itr);
           }
       }
       while(!q.isEmpty()){
           T curr = q.poll();
           res.add(curr);
           for(Pair<T,Integer>itr:adjL.get(curr)){
               indegree.put(itr.first, indegree.getOrDefault(itr.first,0)-1);
               if(indegree.get(itr.first)==0){
                   q.add(itr.first);
               }
           }
       }
        return res;
    }

    void DFS_TS_recursion(T node,Stack<T> st){
        markVisited(node);
        for(Pair<T,Integer>itr:adjL.get(node)){
            if(!checkVisited(itr.first)){
                DFS_TS_recursion(itr.first,st);
            }
        }
        st.add(node);
    }

    List<T> DFS_TopoSort(){
        Stack<T> st = new Stack<>();
         for(T temp:adjL.keySet()){
             if(!checkVisited(temp)){
              DFS_TS_recursion(temp,st);
             }
         }
         List<T> res = new ArrayList<>();
         while(!st.isEmpty()){
             res.add(st.pop());
         }
         return res;
    }
}




public class CycleDetection {

  public static void main(String [] args) {
      AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
      graph.addUndirectedEdge(0,1,3);
      graph.addUndirectedEdge(1,2,4);
      graph.addUndirectedEdge(0,3,5);
      graph.addUndirectedEdge(3,5,6);
      graph.addUndirectedEdge(1,4,7);

      graph.printGraph();

      Undirected_Cycle_Detection<Integer> checker = new Undirected_Cycle_Detection<Integer>(graph.getAdjL());
      System.out.println(checker.BFS_detection());
      System.out.println(checker.DFS_detection());



      User u1 = new User("Suvidh",20);
      User u2 = new User("Random",20);
      User u3 = new User("Random3",30);
      AdjacencyListGraph<User> graph3 = new AdjacencyListGraph<>();
      graph3.addDirectedEdge(u1,u2,4);
      graph3.addDirectedEdge(u2,u3,5);

      graph3.printGraph();

      Directed_Cycle_Detection<User> checker2 = new Directed_Cycle_Detection<>(graph3.getAdjL());
      System.out.println(checker2.BFS_TopoSort());
      System.out.println(checker2.DFS_TopoSort());
  }
}
