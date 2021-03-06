
package createGraph;

import java.util.ArrayList;

/**
 *
 * @author KÜBRA NUR
 */
class funQueue {
   
	private int front;
	private int rear;
	private ArrayList<String> functionName;
	
	public funQueue(){
		functionName = new ArrayList<String>();
		front = 0;
		rear = -1;
	}
	
	public void insert(String s){
		/*
		if(rear == functionName.size()-1){
			rear = -1;
		}
		*/
		//functionName.add(++rear, s);
		functionName.add(s);
	}
	
	public String remove(){
		
		String temp = functionName.get(front);
		functionName.remove(front);
		/*if(front==functionName.size()){
			front=0;
		}*/
		return temp;
	}

	public boolean isEmpty(){
		if(functionName.size()==0){
			return true;
		}
		else
		{
			return false;
		}
		//return(rear+1==front || front+functionName.size()-1==rear);
	}
	
	public void display(){
		front=0;
		rear=functionName.size()-1;
		while(front <= rear){
			System.out.println(functionName.get(front));
			front++;
		}
	}
	
	public int length()
	{
		return functionName.size();
	}
	
	public ArrayList<String> getQueue()
	{
		return functionName;
	}
}


