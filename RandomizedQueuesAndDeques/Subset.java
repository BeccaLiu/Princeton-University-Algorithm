package RandomizedQueuesAndDeques;

import edu.princeton.cs.introcs.StdIn;

public class Subset {

	 public static void main(String[] args){
		 int k=Integer.parseInt(args[0]);
		 RandomizedQueue<String> rq=new RandomizedQueue<String>();
		 while(!StdIn.isEmpty()){
			 rq.enqueue(StdIn.readString());
		 }
		 for(int i=0;i<k;i++){
			 System.out.println(rq.dequeue());
		 }
		
	 }
}