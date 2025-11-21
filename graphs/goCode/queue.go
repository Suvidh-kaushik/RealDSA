package main

type Queue struct{
	data []int
	head int
}

func intializeQueue() *Queue{
	return &Queue{data:make([]int,0),head:0}
}

func (q*Queue) enqueue(x int){
	q.data = append(q.data,x);
}

func (q *Queue) dequeue() int{
   val:=q.data[q.head]
   q.head++;
   return val
}

func (q *Queue) isEmpty() bool{
	return q.head >= len(q.data);
}