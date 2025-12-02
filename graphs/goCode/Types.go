package main

import "fmt"

type Edge struct{
	node int
	wt int
}

// Graph interface to be used for writing common functionality for adjacencyList and adjacencyMatrix liek BFS/DFS
type Graph interface{
	addUndirectedEdge(u,v,wt int)
	addDirectedEdge(u,v,wt int)
	getNeighbors(u int) []Edge
	printGraph()
}
// adjacency List representation of graph

type adjLGraph struct{
    adjL map[int][]Edge
}

func intializeAdjLGraph(size int) Graph{
	return &adjLGraph{adjL: make(map[int][]Edge,size)}
}


func (g *adjLGraph) addUndirectedEdge (u,v,wt int){
   g.adjL[u] = append(g.adjL[u],Edge{v,wt});
   g.adjL[v] = append(g.adjL[v],Edge{u,wt});
}

func (g *adjLGraph) addDirectedEdge (u,v,wt int){
	g.adjL[u] = append(g.adjL[u],Edge{v,wt});
}

func (g *adjLGraph) printGraph(){
	fmt.Println("Adjacency List:")
	for i := range g.adjL {
        fmt.Println(i,"--->",g.adjL[i])
	}
	fmt.Println()
}

func (g *adjLGraph) getNeighbors(u int) []Edge{
     return g.adjL[u];
}



// adjacency matrix representation for graph

type adjMGraph struct{
	size int
	adjM [][]int
}


func intializeAdjMGraph(size int) Graph{
   adjM := make([][]int,size)

   for i:=range adjM{
	adjM[i] = make([]int,size)
   }

   return &adjMGraph{
	size: size,
	adjM: adjM,
   }
}

func (g *adjMGraph) addUndirectedEdge(u,v,wt int){
	g.adjM[u][v] = wt;
	g.adjM[v][u] = wt;
}

func (g *adjMGraph) addDirectedEdge(u,v,wt int){
	g.adjM[u][v] = wt;
}

func (g adjMGraph) printGraph(){
	fmt.Println("Adjacency Matrix: ")
	for i:=0;i<g.size;i++{
       for j:=0;j<g.size;j++{
		 fmt.Print(g.adjM[i][j]," ")
	   }
	   fmt.Println()
	}
	fmt.Println()
}

func (g adjMGraph) getNeighbors(u int)[] Edge{
	var result []Edge;
	for i:=0;i<g.size;i++{
		if(g.adjM[u][i]!=0){
			result=append(result,Edge{i,g.adjM[u][i]});
		}
	}
	return result;
}