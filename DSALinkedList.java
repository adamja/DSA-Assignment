
import java.io.*;
import java.util.*;

public class DSALinkedList
{
	private DSAListNode head;
	private DSAListNode tail;
	
	public DSALinkedList()
	{
		head = null;		// set head to null
		tail = null;		// set tail to null
	}
	
	public boolean isEmpty()
	{
		boolean isEmpty = false;
		
		if ( head == null ) {
			isEmpty = true;
		}
		
		return isEmpty;			// return true if linked list is empty
	}
	
	public void insertFirst( Object inValue )
	{
		DSAListNode newNode = new DSAListNode(inValue);
		
		if ( isEmpty() ) {
			head = tail = newNode;		// if first node, set both head and tail to the new nodes value
		}
		else {
			newNode.setNext(head); 			// point new node to current head
			head.setPrevious(newNode);		// link current head's previous to new node
			head = newNode;					// make new node the new head
			head.setPrevious(null);			// make the new tail's previous point to null
		}
	}
	
	public void insertLast( Object inValue )
	{
		DSAListNode newNode = new DSAListNode(inValue);
		
		if ( isEmpty() ) {
			tail = head = newNode;
		}
		else {
			newNode.setPrevious(tail);		// point new node to current tail
			tail.setNext(newNode);			// link current tail's next to new node
			tail = newNode;					// make new node the new tail
			tail.setPrevious(null);			// make the new tail's previous point to null
		}
	}
	
	public Object peekFirst()
	{
		Object nodeValue = null;
		
		if ( !isEmpty() ) {
			nodeValue = head.getValue();
		}
		
		return nodeValue;		// return first value
	}

	public Object peekLast()
	{
		Object nodeValue = null;
		
		if ( !isEmpty() ) { 
			nodeValue = tail.getValue();
		}
		
		return nodeValue;		// return last value
	}
	
	public Object removeFirst()
	{
		Object nodeValue = null;
		
		if ( isEmpty() ) { 
			throw new IllegalArgumentException( "The list is empty" );
		}
		else {
			nodeValue = head.getValue();
			head = head.getNext();			// move head and point to second value
		}
		
		return nodeValue;					// return first value
	}
	
	public Object removeLast()
	{
		Object nodeValue = null;
		
		if ( isEmpty() ) { 
			throw new IllegalArgumentException( "The list is empty" );
		}
		else {
			nodeValue = tail.getValue();
			tail = tail.getNext();			// move tail and point to second last value
		}
		
		return nodeValue;					//return last value
	}

/*********************************************************************/
	private class DSAListNode
	{	// private inner class
		private Object value;
		private DSAListNode next;
		private DSAListNode previous;
			
		public DSAListNode( Object inValue )
		{
			value = inValue;
			next = null;
			previous = null;
		}
		
		public Object getValue()
		{
			return value;
		}
		
		public void setValue( Object inValue )
		{
			value = inValue;
		}
		
		public DSAListNode getNext()
		{
			return next;
		}
		
		public DSAListNode getPrevious()
		{
			return previous;
		}
		
		public void setNext( DSAListNode newNext )
		{
			next = newNext;
		}
		
		public void setPrevious( DSAListNode newPrevious )
		{
			previous = newPrevious;
		}
	} // End DSAListNode class
} // End DSALinkedList class
