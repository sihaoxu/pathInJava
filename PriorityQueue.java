import java.util.ArrayList;

// PRIORITYQUEUE.JAVA
// A priority queue class supporting sundry operations needed for
// Dijkstra's algorithm.
//

class PriorityQueue<T> {
	
    //public T[] list=new T[5000];;
    //list=new T[5000];
	//public Handle[] handleList=new Handle[5000];
	public ArrayList<Element<T>> nodeList = new ArrayList<Element<T>>();
	
	//@SuppressWarnings("unchecked")
	//public Element<T>[] nodeList=new Element[5000];
	
	

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
    	nodeList.add(0,null);
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
    	Element<T> e=new Element<T>(key,value,handle);
    	nodeList.add(numberOfNode,e);
    	//nodeList[n]=new Element<T>(key, value, handle);
    	int noleaf=(int)Math.floor(n/2);
    	if(noleaf<1)
    	{
    		noleaf=1;
    	}
    	while((n>1)&&(key<nodeList.get(noleaf).key))
    	{
        	Element<T> s = nodeList.get(n);
        	nodeList.set(n, nodeList.get(noleaf));
        	nodeList.set(noleaf,  s);
        	nodeList.get(n).handle.keyValue = n;
        	nodeList.get(noleaf).handle.keyValue = noleaf;
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
    		return nodeList.get(1).key;
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
    	int value=nodeList.get(1).handle.keyValue;

    	T tempele=nodeList.get(1).element;
    	nodeList.get(1).handle.keyValue=0;
    	nodeList.set(1, nodeList.get(numberOfNode-1));
    	//System.out.print(nodeList[numberOfNode-2].handle.keyValue);
    	//System.out.print(nodeList[1].handle.keyValue);
    	nodeList.get(1).handle.keyValue=value;
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
    		int leftchild=nodeList.get(2*i).key;
    		int rightchild=nodeList.get(2*i+1).key;
    		if((numberOfNode<=2*i)||(leftchild<rightchild))
    		{
    			j=2*i;
    		}
    		else
    		{
    			j=2*i+1;
    		}
    		if(nodeList.get(j).key<nodeList.get(i).key)
    		{
            	Element<T> s = nodeList.get(i);
            	nodeList.set(i, nodeList.get(j));
            	nodeList.set(j, s);
            	nodeList.get(i).handle.keyValue = i;
            	nodeList.get(j).handle.keyValue = j;
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
    	if(nodeList.get(keyvalue)==null)
    	{
    		return false;
    	}
    	if(newkey>=nodeList.get(keyvalue).key)
    	{
    		return false;
    	}
    	nodeList.get(keyvalue).key=newkey;
    	int noleaf=(int)Math.floor(keyvalue/2);
    	while((keyvalue>1)&&(newkey<nodeList.get(noleaf).key))
    	{
        	Element<T> s = nodeList.get(keyvalue);
        	nodeList.set(keyvalue,nodeList.get(noleaf));
        	nodeList.set(noleaf,s);
        	nodeList.get(keyvalue).handle.keyValue = keyvalue;
        	nodeList.get(noleaf).handle.keyValue = noleaf;
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
    	if(nodeList.get(handle.keyValue)!=null)
    	{
    		int handlekey=nodeList.get(handle.keyValue).key;
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
    	if(nodeList.get(handle.keyValue)!=null)
    	{
    		T value=nodeList.get(handle.keyValue).element;
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
    		elementList=elementList+" "+nodeList.get(i).element;
    	}
	    return elementList;
    }
    




}