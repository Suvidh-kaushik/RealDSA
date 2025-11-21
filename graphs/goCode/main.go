package main


func main(){
   adjLGraph := intializeAdjLGraph(5);


   adjLGraph.addUndirectedEdge(0,1);
   adjLGraph.addUndirectedEdge(1,2);
   adjLGraph.addUndirectedEdge(0,3);
   adjLGraph.addUndirectedEdge(3,4);
   adjLGraph.addUndirectedEdge(1,4);

   adjLGraph.printGraph();


   adjMGraph := intializeAdjMGraph(5);

   adjMGraph.addUndirectedEdge(0,1);
   adjMGraph.addUndirectedEdge(1,2);
   adjMGraph.addUndirectedEdge(0,3);
   adjMGraph.addUndirectedEdge(3,4);
   adjMGraph.addUndirectedEdge(1,4);
   
   adjMGraph.printGraph();

   BFS(adjLGraph,0);
}