
import java.util.*;

public class BinarySearchTree
{

/**********************************************************************************/
	// TREENODE CLASS
	private class TreeNode
	{
		private String key;
		private Object value;
		private TreeNode leftChild;
		private TreeNode rightChild;
		
		// Alternate constructor
		public TreeNode( String inKey, Object inVal )
		{
			if (inKey == null ) {
				throw new IllegalArgumentException("Key cannot be null");
			}
			key = inKey;
			value = inVal;
			leftChild = null;
			rightChild = null;
		}
		// Accessors and Mutators
		public String getKey()
		{
			return key;
		}
		
		public Object getValue()
		{
			return value;
		}
		
		public TreeNode getLeft()
		{
			return leftChild;
		}
		
		public void setLeft( TreeNode newLeft )
		{
			leftChild = newLeft;
		}
		
		public TreeNode getRight()
		{
			return rightChild;
		}
		
		public void setRight( TreeNode newRight)
		{
			rightChild = newRight;
		}
	}// End TreeNode Class
	
/**********************************************************************************/
	// BINARYSEARCHTREE CLASS
	private TreeNode root;
	
	// Default constructor
	public BinarySearchTree()
	{
		root = null;
	}
	
	// INSERT
	public void insert( String key, Object value )
	{
		root = insertRecursive(key, value, root);
	}
	
	private TreeNode insertRecursive( String key, Object value, TreeNode currNode)
	{
		TreeNode updateNode = currNode;
		
		if (currNode == null) {
			updateNode = new TreeNode(key, value);
		}
		else if (key.equals(currNode.getKey())) {
			throw new IllegalArgumentException("Cannot have the same key more than once in the tree");
		}
		else if (key.compareTo(currNode.getKey()) < 0) {
			currNode.setLeft(insertRecursive(key, value, currNode.getLeft()));
		}
		else {
			currNode.setRight(insertRecursive(key, value, currNode.getRight()));
		}	
		
		return updateNode;
	}
	
	// TRAVERSE TREE - Checks every node in the tree to see if it matches input string search
	public DSAQueue traverseTree(String search)
	{
		DSAQueue queue = new DSAQueue();
		return traverseTreeRecursive(search, root, queue);		// start searching from root
	}
	
	public DSAQueue traverseTreeRecursive( String search, TreeNode currNode, DSAQueue queue )
	{
		if (currNode == null) {
			//end
		}
		else {
			if (match(search, currNode.getKey())) {		// if search matches currNode
				queue.enqueue(currNode.getValue());		// add to queue
			}
			traverseTreeRecursive(search, currNode.getLeft(), queue );		// search every left branch of the tree
			traverseTreeRecursive(search, currNode.getRight(), queue );		// search every right branch of the tree
		}
			
		return queue;
	}
	
	// MATCH
	public boolean match( String search, String currKey )
	{
		boolean match = true;
		int length;
		
		if (search.length() <= currKey.length()) {
			for (int ii = 0; ii <= search.length() -1; ii++) {		// each char of string search matches charAt currKey
				if ( search.charAt(ii) != currKey.charAt(ii) ) {	// return match as true
					match = false;									// otherwise return false
				}
			}
		}
		else {
			match = false;											// if search string is longer than currKey string
		}															// match is automatically false
		
		return match;
	}
}// End BinarySearchTree class