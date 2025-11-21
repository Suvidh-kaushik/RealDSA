package main


func main(){
   adjLGraph := intializeAdjLGraph(5);


   adjLGraph.addUndirectedEdge(0,1,3);
   adjLGraph.addUndirectedEdge(1,2,4);
   adjLGraph.addUndirectedEdge(0,3,5);
   adjLGraph.addUndirectedEdge(3,4,6);
   adjLGraph.addUndirectedEdge(1,4,7);

   adjLGraph.printGraph();


   adjMGraph := intializeAdjMGraph(5);

   adjMGraph.addUndirectedEdge(0,1,3);
   adjMGraph.addUndirectedEdge(1,2,4);
   adjMGraph.addUndirectedEdge(0,3,5);
   adjMGraph.addUndirectedEdge(3,4,6);
   adjMGraph.addUndirectedEdge(1,4,7);
   
   adjMGraph.printGraph();

   BFS(adjLGraph,0);

   DFS(adjLGraph,0);
}