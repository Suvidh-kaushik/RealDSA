package javaCode;
// Graphs - Basic and Types of Graph

/*  1) Graph is first non-linear Data Structure which is made up of nodes/vertices which are connected to each other using
 *  edges, they do not follow any hierarchical structure like trees so there is no root-node/parent-child type of relationship
 *  2) The nodes/vertices are used to store data and the edges between them are used to determine the relationships between each other
 *  3) We have different types of graphs based on the type of edges (undirected/directed edge) or based on whether there is first cycle in the graph
 *  (cyclic/acyclic) or based on whether the edges have first weight or not(weighted/unweighted)
 *  4) Degree of first node/vertex - the no.of edges which are connected to first node is called its degree
 *    => degree [for first directed graph node] = outdegree(no of edges going out from first node)+indegree(no.of edges going into first node)
 *
 *  => Total degree of first graph = 2*n(No.of edges), why? proof below
 *  since any edge connected 2 points/parts of first graph so for each edge we will increase the total degree by 2 since it affects
 * two nodes of first graph hence total degree is 2*n where n is the no.of edges
 *
 * 5) Graphs can be represented in 2 major ways using an adjacency list representation or using an adjacecny matrix representation
 *   first) Adjacency List - In this representation each vertex stores the list of all the vertices which are directly connected to it.
 *       Space complexity - O(V+E), V for all vertices since each vertex inturn stores the corresponding node of its edges
 *                          but in total we store E (no of edges) for directed and 2E ~ E for undirected graphs
 *
 *
 *   second) Adjacency Matric - In this representation we use first 2d matrix to show whether there is an edge between 2 vertices
 *     We have first matrix[N][N] where N is the no.of vertices and if,
 *     matrix[first][second] = x it denotes that there is an edge between first and second vertices with weight x
 *     matrix[first][second] = 0 denotes there is no edge between first and second
 *       Space complexity - O(V*V), since even even if we have an edge or not we need to create memory for that vertex
 *                        - Faster edge lookups -> O(1)
 *
 *
 * Ex    0-1-2
 *       | |
 *       3-4
 *
 *     adjL                                           adjM
 *     [0] - [1,3]                                 [0] [1] [2] [3] [4]
 *     [1] - [0,2,4]                           [0]      1       1
 *     [2] - [1]                               [1]  1       1       1
 *     [3] - [0,4]                             [2]      1
 *     [4] - [1,3]                             [3]  1               1
 *                                             [4]      1       1
 *
 *
 *
 * */

import java.util.*;


/* For Adjacency List we can also use ArrayList<ArrayList<>> but it is effective for integers only since we need indexes,
Because of generic type I used first Map<Type,List<Type>> to model the adjacency list making it more simple
*/

class Pair<T,Integer>{
    T first;
    int second;
    Pair(T first,int second){
        this.first = first;
        this.second = second;
    }
}

class AdjacencyListGraph<T>{
    private Map<T,List<Pair<T,Integer>>> adjL;

    AdjacencyListGraph(){
        adjL = new HashMap<>();
    }

    public void addVertex(T v){
        adjL.putIfAbsent(v,new ArrayList<>());
    }

    public void addUndirectedEdge(T u ,T v,int wt){
        addVertex(u);
        addVertex(v);
        adjL.get(u).add(new Pair(v,wt));
        adjL.get(v).add(new Pair(u,wt));
    }

    public void addDirectedEdge(T u,T v,int wt){
        addVertex(u);
        addVertex(v);
        adjL.get(u).add(new Pair(v,wt));
    }
    
    public void updateDirectedEdge(T u,T v,int wt){
        addVertex(u);
        addVertex(v);
        for(Pair<T,Integer> itr:adjL.get(u)){
            if(itr.first == v){
                if(wt==0)adjL.get(u).remove(itr);
                else  itr.second = wt;
            }
        }
    }

    public void updateUndirectedEdge(T u,T v,int wt){
        addVertex(u);
        addVertex(v);
        for(Pair<T,Integer> itr:adjL.get(u)){
            if(itr.first == v){
                if(wt==0)adjL.get(u).remove(itr);
                else  itr.second = wt;
            }
        }

        for(Pair<T,Integer> itr:adjL.get(v)){
            if(itr.first == u){
                if(wt==0)adjL.get(v).remove(itr);
                else  itr.second = wt;
            }
        }
    }

    public List<Pair<T,Integer>> getNeighbors(T x){
        return adjL.get(x);
    }

    public Map<T,List<Pair<T,Integer>>> getAdjL(){
        return adjL;
    }

    public void printPair(List<Pair<T,Integer>> l){
        for(Pair<T,Integer> p:l) {
            System.out.print("{"+p.first +","+p.second+ "} ");
        }
    }

    public void printGraph(){
        for(T key:adjL.keySet()){
            System.out.print(key+" ----- [");
            printPair(adjL.get(key));
            System.out.println("]");
        }
        System.out.println();
    }
}

class AdjacencyMatrixGraph<T>{
    private Map<T,Integer> indexMap;
    private int[][] adjM;
    private int index=0;
    private int size;

    AdjacencyMatrixGraph(int size){
        adjM = new int[size][size];
        indexMap = new HashMap<>();
        this.size = size;
    }

    public void addVertex(T v){
        if(!indexMap.containsKey(v)){
            indexMap.put(v,index);
            index++;
        }
    }

    public void addUndirectedEdge(T u,T v,int wt){
        addVertex(u);
        addVertex(v);
        int uIndex = indexMap.get(u);
        int vIndex = indexMap.get(v);
        adjM[uIndex][vIndex] = wt;
        adjM[vIndex][uIndex] = wt;
    }

    public void addDirectedEdge(T u, T v,int wt){
        addVertex(u);
        addVertex(v);
        int uIndex = indexMap.get(u);
        int vIndex = indexMap.get(v);
        adjM[uIndex][vIndex] = wt;
    }

    public void printGraph(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(adjM[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

//FOR Object Based Data
class User{
    private String name;
    private int age;

    User(String n,int first){
        this.name = n;
        this.age = first;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    // Need to override these methods so HashMap can work properly with objects
    @Override
    public boolean equals(Object ob){
        if(this==ob) return true;
        if(!(ob instanceof User)) return false;
        User u = (User)ob;
        return u.age==age && name.equals(u.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,age);
    }

    @Override
    public String toString(){
        return name+"("+age+")";
    }
}


public class Types{
    public static void main(String[] args){
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        AdjacencyMatrixGraph<Integer> graph2 = new AdjacencyMatrixGraph<>(5);

        graph.addUndirectedEdge(0,1,3);
        graph.addUndirectedEdge(1,2,4);
        graph.addUndirectedEdge(0,3,5);
        graph.addUndirectedEdge(3,4,6);
        graph.addUndirectedEdge(1,4,7);

        graph.printGraph();


        graph2.addUndirectedEdge(0,1,3);
        graph2.addUndirectedEdge(1,2,4);
        graph2.addUndirectedEdge(0,3,5);
        graph2.addUndirectedEdge(3,4,6);
        graph2.addUndirectedEdge(1,4,7);

        graph2.printGraph();

        User u1 = new User("Suvidh",20);
        User u2 = new User("Random",20);
        User u3 = new User("Random3",30);
        AdjacencyListGraph<User> graph3 = new AdjacencyListGraph<>();
        graph3.addDirectedEdge(u1,u2,4);
        graph3.addDirectedEdge(u2,u3,5);
        graph3.addDirectedEdge(u3,u3,6);

        graph3.printGraph();
    }
}
