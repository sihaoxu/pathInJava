// PRIORITYQUEUE.JAVA
// A priority queue class supporting sundry operations needed for
// Dijkstra's algorithm.
//

class PriorityQueue<T> {
	

	public Element<T>[] nodeList=new Element[5000];
	

	public int numberOfNode;
    
    // constructor
    //
	class Element<T> {
		
	   public Handle handle;
	   public int key;   
	   public T element;  

	   public Element(int k, T e, Handle h){
		   key = k;
			element = e;
			handle = h;
	}
	}
    public PriorityQueue()
    {
    	
        numberOfNode = 1;
    }
    
    
    public boolean isEmpty()
    {
    	
    	if(numberOfNode==1)
    	{
    	   return true;
    	}
    	else{
    	   return false;
    	}
    }
    
    
    Handle insert(int key, T value)
    {
    	
    	/*if(nodeList.length==numberOfNode)
    	{
    	   Element<T>[] store = nodeList;
    	   nodeList = new Element[numberOfNode+100];
    	   for(int i=0; i<numberOfNode; i++)
    	   {
    			 nodeList[i] = store[i];
    	   }
    	}*/

    	int n=numberOfNode;
    	Handle handle=new Handle(n);
    	nodeList[n]=new Element<T>(key, value, handle);
    	int noleaf=(int)Math.floor(n/2);
    	if(noleaf<1)
    	{
    		noleaf=1;
    	}
    	while((n>1)&&(key<nodeList[noleaf].key))
    	{
        	Element<T> s = nodeList[n];
        	nodeList[n] = nodeList[noleaf];
        	nodeList[noleaf] = s;
        	nodeList[n].handle.keyValue = n;
        	nodeList[noleaf].handle.keyValue = noleaf;
    		n=(int)Math.floor(n/2);
    		noleaf=(int)Math.floor(n/2);
    		if(noleaf<1)
        	{
        		noleaf=1;
        	}
    	}
    	numberOfNode=numberOfNode+1;
    	return handle;
    	

    }
    
   
    public int min()
    {	
    	
    	if(isEmpty()==false)
    	{
    		return nodeList[1].key;
    	}
    	else{
    		return 0;
    	}
    }
    
   
    public T extractMin()
    {
    	
    	if(isEmpty()==true)
    	{
    		return null;
    	}
    	int value=nodeList[1].handle.keyValue;

    	T tempele=nodeList[1].element;
    	nodeList[1].handle.keyValue=0;
    	nodeList[1]=nodeList[numberOfNode-1];
    	//System.out.print(nodeList[numberOfNode-2].handle.keyValue);
    	//System.out.print(nodeList[1].handle.keyValue);
    	nodeList[1].handle.keyValue=value;
    	numberOfNode--;
    	heapify(1);
    	return tempele;

    }
    
    private void heapify(int i)
    {
    	int m=(int)Math.floor(numberOfNode/2);
    	if(i<=m)
    	{
    		int j;
    		int leftchild=nodeList[2*i].key;
    		int rightchild=nodeList[2*i+1].key;
    		if((numberOfNode<=2*i)||(leftchild<rightchild))
    		{
    			j=2*i;
    		}
    		else
    		{
    			j=2*i+1;
    		}
    		if(nodeList[j].key<nodeList[i].key)
    		{
            	Element<T> s = nodeList[i];
            	nodeList[i] = nodeList[j];
            	nodeList[j] = s;
            	nodeList[i].handle.keyValue = i;
            	nodeList[j].handle.keyValue = j;
    			heapify(j);
    		}
    	}

    }
    
    
   
    public boolean decreaseKey(Handle h, int newkey)
    {
    	
    	int keyvalue=h.keyValue;
    	if(keyvalue>numberOfNode)
    	{
    		return false;
    	}
    	if(nodeList[keyvalue]==null)
    	{
    		return false;
    	}
    	if(newkey>=nodeList[keyvalue].key)
    	{
    		return false;
    	}
    	nodeList[keyvalue].key=newkey;
    	int noleaf=(int)Math.floor(keyvalue/2);
    	while((keyvalue>1)&&(newkey<nodeList[noleaf].key))
    	{
        	Element<T> s = nodeList[keyvalue];
        	nodeList[keyvalue] = nodeList[noleaf];
        	nodeList[noleaf] = s;
        	nodeList[keyvalue].handle.keyValue = keyvalue;
        	nodeList[noleaf].handle.keyValue = noleaf;
    		keyvalue=(int)Math.floor(keyvalue/2);
    		noleaf=(int)Math.floor(keyvalue/2);
    		if(noleaf<1)
        	{
        		noleaf=1;
        	}
    	}
    	h.keyValue=keyvalue;
        return true;
  
    }
    
    
   
    public int handleGetKey(Handle h)
    {
    	Handle handle=h;
    	if(nodeList[handle.keyValue]!=null)
    	{
    		int handlekey=nodeList[handle.keyValue].key;
    		return handlekey;
    	} 
    	else
    	{
    		return 0;
    	}
    }

  
    public T handleGetValue(Handle h)
    {	
    	Handle handle=h;
    	if(nodeList[handle.keyValue]!=null)
    	{
    		T value=nodeList[handle.keyValue].element;
    		return value;
    	}
    	else
    	{
    		return null;
    	}
    } 
    
 
    public String toString()
    {
    	String elementList="";
    	for(int i=1;i<numberOfNode;i++)
    	{
    		elementList=elementList+" "+nodeList[i].element;
    	}
	    return elementList;
    }
    




}