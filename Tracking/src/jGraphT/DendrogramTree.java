package jGraphT;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.ImageIcon;



public class DendrogramTree {
// Root node pointer. Will be null for an empty tree.
private static TreeNode root;
private static TreeNode current;

private static class TreeNode {
 TreeNode left;
 TreeNode right;
 int icon;

 TreeNode(int newdata) {
   left = null;
   right = null;
   icon = newdata;
 }
}

public void DendrogramTree() {
 root = null;
}



public void insert(int data,String pos) {
 root = insert(root, data, pos);
}

private TreeNode insert(TreeNode node, int imgicon,String pos) {
 if (node==null) {
   node = new TreeNode(imgicon);
 }
 else {
   if (pos==null) {
     node.left = insert(node.left, imgicon, pos);
   }
   else {
     node.right = insert(node.right, imgicon, pos);
   }
 }

 return(node); // in any case, return the new pointer to the caller
}


public static void main(String args[])
{
	
	DendrogramTree temptrial=new DendrogramTree();
	temptrial.insert(5,null);
	temptrial.insert(6,"right");
	temptrial.insert(7,null);
	temptrial.insert(8,null);
	temptrial.insert(9,null);
	temptrial.insert(10,null);
	temptrial.insert(11,"r");
	
	
	Queue<TreeNode> bfsqueue=new LinkedList<TreeNode>();
	bfsqueue.add(root);

	HashSet<TreeNode> hashset=new HashSet<>();
	Queue<Integer> level=new LinkedList<>();
	level.add(0);
	int arr[][]=new int[5][5];
	arr[0][0]=bfsqueue.peek().icon;
	int index=0;
	int prevlev=0;
	while(bfsqueue.size()>0)
	{
		TreeNode temp=bfsqueue.poll();
		int templev=level.poll();
		if(templev>prevlev)
			{
			index=0;
			prevlev=templev;
			
			}
		if(hashset.contains(temp))
			continue;
		else
		{
			hashset.add(temp);
			if(temp.left!=null)
			if(!hashset.contains(temp.left))
				{
				bfsqueue.add(temp.left);
				level.add(templev+1);
				arr[templev+1][index]=temp.left.icon;
				index++;
			
				}
			if(temp.right!=null)
			if(!hashset.contains(temp.right)) {
				bfsqueue.add(temp.right);
				level.add(templev+1);
				arr[templev+1][index]=temp.right.icon;
				index++;
			}
			
		}
		
	}
	
	for(int a[]:arr)
		{
		for(int j:a)
			if(j!=0)
				System.out.print(j+" ");
		System.out.println();
		}
}
}