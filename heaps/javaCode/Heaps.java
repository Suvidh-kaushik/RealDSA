package javaCode;


/* Heap - a)A heap is a special type of binary tree where the highest/lowest priority element is always at the top and easily accessible
 *         b) It must follow two rules
 *              - Heap is a complete binary tree (All levels of the tree are completely filled except the last, the last is filled L to R)
 *                 i) Max Heap - the parent node is always greater than/equal to its children
 *                ii) Min Heap - the parent node is always less than/equal to its children
 *         c) Heap is actually a complete binary tree so it can be represented in the form of a tree but since we say it is complete we utilize this property
 *     to have an array based format of representing heap
 *       For a 0-based indexing if a node is at index i ,
 *       its left child is at 2*i+1
 *       its right child is at 2*i+2
 *       its parent is at (i-1)/2
 * -
 *
 * */





import java.util.*;


class Node{
     int value;
     Node left,right,parent;
    Node(int v){
        this.value = v;
        left=right=parent=null;
    }
}



class BuildTreeHeap{
    private Node root;
    private Boolean isMaxHeap;
    public BuildTreeHeap(boolean isMaxHeap)
    {
        root=null;
        this.isMaxHeap = isMaxHeap;
    }

    public void insert(int value){
        Node newN = new Node(value);

        if(root==null){
            root = newN;
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(curr.left==null){
                curr.left = newN;
                newN.parent = curr;
                heapifyUp(newN);
                break;
            }
            else q.add(curr.left);

            if(curr.right==null){
                curr.right = newN;
                newN.parent = curr;
                heapifyUp(newN);
                break;
            }
            else q.add(curr.right);
        }
    }

    private void heapifyUp(Node node){
        while((node.parent!=null) && (isMaxHeap?node.value>node.parent.value:node.value<node.parent.value)){
            int temp = node.value;
            node.value = node.parent.value;
            node.parent.value = temp;
            node = node.parent;
        }
    }

    public int peek() throws Exception{
        if(root==null) throw new Exception("Heap is Empty");
        return root.value;
    }

    public void delete()throws Exception{
        if(root==null) throw new Exception("Heap is empty");

        int node_value = root.value;
        Node lastNode = getLastNode();
        System.out.println(lastNode.value);
        if(lastNode==root){
            root=null;
            return;
        }
        root.value = lastNode.value;
        deleteNode(lastNode);
        heapifyDown(root);
    }


    private void heapifyDown(Node node){
        while(node!=null){
            Node largest = node;
            if(node.left!=null && checkCondition(node,largest,true,isMaxHeap)){
                largest = node.left;
            }

            if(node.right!=null && checkCondition(node,largest,false,isMaxHeap)){
                largest = node.right;
            }

            if(largest == node) break;

            int temp = node.value;
            node.value = largest.value;
            largest.value = temp;

            node=largest;
        }
    }

    private Boolean checkCondition(Node node,Node largest,Boolean isLeftNode,Boolean isMaxHeap){
        if (isMaxHeap) {
            return isLeftNode ? node.left.value > largest.value : node.right.value > largest.value;
        } else {
            return isLeftNode ? node.left.value < largest.value : node.right.value < largest.value;
        }
    }

    private Node getLastNode(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node last = null;
        while(!q.isEmpty()){
            last = q.poll();
            if(last.left!=null) q.add(last.left);
            if(last.right!=null) q.add(last.right);
        }
        return last;
    }

    private void deleteNode(Node n){
        if(n==root){
            root=null;
            return;
        }
        Node parent = n.parent;
        if(parent.left==n) parent.left=null;
        else parent.right=null;
    }

    public void printHeap(){
        if(root==null){
            System.out.println("Heap is empty");
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node temp = q.poll();
            System.out.print(temp.value+" ");
            if(temp.left!=null) q.add(temp.left);
            if(temp.right!=null) q.add(temp.right);
        }
        System.out.println();
    }

    
}


class Heaps{
    public static void main(String[] args) throws Exception{
          BuildTreeHeap hp = new BuildTreeHeap(true);  //maxHeap
          hp.printHeap();
          hp.insert(30);
          hp.insert(60);
          hp.insert(50);
          hp.insert(40);
          hp.insert(10);
          hp.insert(70);
          hp.printHeap();
          hp.delete();
          //hp.peek();
          hp.printHeap();

          System.out.println("---------------------------");

          BuildTreeHeap hp2 = new BuildTreeHeap(false);
          hp2.printHeap();
          hp2.insert(30);
          hp2.insert(60);
          hp2.insert(50);
          hp2.insert(40);
          hp2.insert(10);
          hp2.insert(70);
          hp2.printHeap();
          hp2.delete();
          hp2.printHeap();
    }
}