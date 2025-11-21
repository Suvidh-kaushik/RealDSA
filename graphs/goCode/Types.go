package main

import "fmt"

// Graph interface to be used for writing common functionality for adjacencyList and adjacencyMatrix liek BFS/DFS
type Graph interface{
	addUndirectedEdge(u,v int)
	addDirectedEdge(U,V int)
	getNeighbors(u int) []int
	printGraph()
}


// adjacency List represntation of graph

type adjLGraph struct{
    adjL [][]int
}

func intializeAdjLGraph(size int) *adjLGraph{
	return &adjLGraph{adjL: make([][]int,size)}
}


func (g *adjLGraph) addUndirectedEdge (u,v int){
   g.adjL[u] = append(g.adjL[u],v);
   g.adjL[v] = append(g.adjL[v],u);
}

func (g *adjLGraph) addDirectedEdge (u,v int){
	g.adjL[u] = append(g.adjL[u],v);
}

func (g adjLGraph) printGraph(){
	fmt.Println("Adjacency List:")
	for i := range g.adjL {
        fmt.Println(i,"--->",g.adjL[i])
	}
	fmt.Println()
}

func (g *adjLGraph) getNeighbors(u int) []int{
     return g.adjL[u];
}



// adjacency matrix representation for graph

type adjMGraph struct{
	size int
	adjM [][]int
}


func intializeAdjMGraph(size int) *adjMGraph{
   adjM := make([][]int,size)

   for i:=range adjM{
	adjM[i] = make([]int,size)
   }

   return &adjMGraph{
	size: size,
	adjM: adjM,
   }
}

func (g *adjMGraph) addUndirectedEdge(u,v int){
	g.adjM[u][v] = 1;
	g.adjM[v][u] = 1;
}

func (g *adjMGraph) addDirectedEdge(u,v int){
	g.adjM[u][v] = 1;
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

func (g adjMGraph) getNeighbors(u int)[] int{
	var result []int;
	for i:=0;i<g.size;i++{
		if(g.adjM[u][i]==1){
			result=append(result,i);
		}
	}
	return result;
}