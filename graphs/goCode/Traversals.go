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
            if !vis[curr]{
			  vis[curr] = true
			  queue.enqueue(curr)
			}
		}
	}
	fmt.Println()
}