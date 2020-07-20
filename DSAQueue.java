
import java.util.*;

public class DSAQueue
{
	
	private int count;
	private DSALinkedList queue;
	
	public DSAQueue()		//Default constructor
	{
		queue = new DSALinkedList();
		count = 0;
	}
	
	
	public int getCount()		//returns count of objects in queue
	{
		return count;
	}
	
	public boolean isEmpty()		//returns true if queue contains no objects
	{
		return queue.isEmpty();
	}

	
	public void enqueue(Object value)		//adds object to queue, throws exception if queue is already full
	{
		queue.insertLast(value);
		count++;		//increment count
	}
	
	public Object dequeue()		//removes an object from the queue and returns it, throws exception if queue was already empty
	{
		Object topVal = peek();
		queue.removeFirst();
		count--;		//decrement count
		
		return topVal;
	}
	
	public Object peek()		//returns the front value of the queue without affecting the queue
	{
		if ( isEmpty() )
			throw new IllegalArgumentException("Queue is empty");
			
		Object topVal = queue.peekFirst();
		
		return topVal;
	}
}