package main

import "fmt"

func BFS(g Graph,startNode int){
	queue := intializeQueue()
    vis := make(map[int]bool)
    
	queue.enqueue(startNode);
	vis[startNode] = true;
	fmt.Print("BFS --- ")
	for !queue.isEmpty() {
		node := queue.dequeue();
		fmt.Print(node,"  ");
		neighbors := g.getNeighbors(node)
        
		for _,curr:= range neighbors{
            if !vis[curr.node]{
			  vis[curr.node] = true
			  queue.enqueue(curr.node)
			}
		}
	}
	fmt.Println()
}


func DFS(g Graph,startNode int){
	vis := make(map[int]bool)
	fmt.Print("DFS --- ")
	var recurse func(int)
	recurse = func(node int){
		vis[node]=true;
		fmt.Print(node,"  ");
		for _,itr:= range g.getNeighbors(node){
			if !vis[itr.node] {
				recurse(itr.node)
			}
		}
	}
	recurse(startNode)
	fmt.Println();
}