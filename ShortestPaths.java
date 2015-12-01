//
// SHORTESTPATHS.JAVA
// Compute shortest paths in a graph.
//
// Your constructor should compute the actual shortest paths and
// maintain all the information needed to reconstruct them.  The
// returnPath() function should use this information to return the
// appropriate path of edge ID's from the start to the given end.
//
// Note that the start and end ID's should be mapped to vertices using
// the graph's get() function.
//
// You can ignore the input and startTime arguments to the constructor
// unless you are doing the extra credit.
//
class ShortestPaths {
	
	public Multigraph graph;
	public PriorityQueue<Node> queue;
	public int max=Integer.MAX_VALUE;
    public Node[] listOfNode;
	public int startIdRecord;
	    class Node{
    	
       public Handle handle;
       public int distance;
       public Vertex vertex;
       public Edge edge;
       public Node parent;
    	
       public Node(Vertex i){
    		vertex=i;
    		parent=null;
    		distance=max;
    	}
     }
    //
    // constructor
    //
    public ShortestPaths(Multigraph G, int startId, 
			 Input input, int startTime) 
    {

    	graph=G;
    	startIdRecord=startId;
    	queue=new PriorityQueue<Node>();
    	listOfNode=new Node[G.nVertices()];
    	Vertex startVertex=graph.get(startId);
    	Node startNode=new Node(startVertex);
    	startNode.distance=0;
    	//startNode.parent=null;
    	//startNode.distance=max;
    	Handle store;
    	store=queue.insert(0, startNode);
    	//startNode.handle=store;
    	startVertex.handle=store;
    	listOfNode[startNode.vertex.id()]=startNode;
    	int z=graph.nVertices();
    	for(int i=0;i<z;i++)
    	{
    		if(i!=startId)
    		{
    			Vertex v=graph.get(i);
    			Node n=new Node(v);
    			//n.distance=max;
    			//n.parent=null;
    			
    			Handle store2=queue.insert(max, n);
    			listOfNode[n.vertex.id()]=n;
    			//n.handle=store2;
    			v.handle=store2;
    		}
    	}

    	
    	while(queue.isEmpty()==false)
    	{
    		Node node=queue.extractMin();
    		if(node.distance==max)
    		{
    			break;
    		}
    		Vertex v=node.vertex;
    		Vertex.EdgeIterator iterator=v.adj();
    		while(iterator.hasNext())
    		{
    			Edge edge=iterator.next();
    			Vertex toVertex=edge.to();
    			int weight=edge.weight();
    			Handle h=toVertex.handle;
    			Node k=queue.handleGetValue(h);
    			int newKey=weight+node.distance;
    			if(queue.decreaseKey(h, newKey)==true)
    			{
    				k.distance=newKey;
    				k.parent=node;
    				k.edge=edge;
    			}
    			
    		}
    	}
    	
    }
    
    //
    // returnPath()
    // Return an array containing a list of edge ID's forming
    // a shortest path from the start vertex to the specified
    // end vertex.
    //
    public int [] returnPath(int endId) 
    { 
	
    	if(startIdRecord==endId)
    	{
    		int empty[] = new int [0];
	        return empty;
    	}
    	//Vertex vertex=graph.get(endId);
    	//Handle h=vertex.handle;
    	//Node n=queue.handleGetValue(h);
    	Node n=listOfNode[endId];
    	Node temp=n;
    	int i=0;
    	for(i=0;temp.parent!=null;i++)
    	{
    		temp=temp.parent;
    	}
    	//while(temp.parent!=null)
    	//{
    		//temp=temp.parent;
    		//i++;
    		//System.out.print(i);
    	//}
    	 int result[]=new int[i];
    	 Node temp2=n;
 
    	 for(i=i-1;temp2.parent!=null;)
    	 {
    		 int id=temp2.edge.id();
    		 result[i]=id;
    		 temp2=temp2.parent;
    		 i--;
    	 }
    	 return result;
    	
    }
    


}