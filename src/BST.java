@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {
    
	protected BSTNode<T> root = null;
	protected static int count = 0;

	public BST() 
	{
    	}
    
	public void clear() 
	{
		root = null;
	}

	public String inorder(BSTNode<T> node) 
	{
		boolean verbose = true;
		if (node != null) {
			String result = "";
			result += inorder(node.left);
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.element.toString() + " ";
			}
			result += inorder(node.right);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() 
	{
		//Your code goes here
		return root==null;
	}

	public BSTNode<T> mirror() 
	{
		//Your code goes here
		return null;
		}


	public BSTNode<T> clone() 
	{
		//Your code goes here
		BST clone = recClone(root);
		return clone.root;

	}

	public void printPreorder() 
	{
		//Your code goes here
		recPrePrint(root);
	}

        public void printPostorder() 
	{
		//Your code goes here
		recPostPrint(root);
	}

	public void insert(T element) 
	{
		//Your code goes here
		BSTNode newNode = new BSTNode(element);
		recInsert(root,newNode);
	}

	public boolean deleteByMerge(T element) 
	{
		//Your code goes here
		return true;
	}

	public boolean deleteByCopy(T element) 
	{
		//Your code goes here
		return true;
	}

	public T search(T element) 
	{
		//Your code goes here
		BSTNode curr=root;
		if(root==null){
			return null;
		}else{
			while(curr!=null){
				if(curr.element.compareTo(element)==0){
					return (T)curr.element;
				}else if(element.compareTo((T)curr.element)>0){
					curr=curr.right;
				}else if(element.compareTo((T)curr.element)<0){
					curr=curr.left;
				}
			}
			return null;
		}
	}

	
	//Helper functions
	public BSTNode recInsert(BSTNode currParent,BSTNode newNode){
		if(root==null){
			root=newNode;
			return newNode;
		}else if(root.left==null && newNode.element.compareTo(root.element)<0){
			root.left=newNode;
		}else if(root.right==null && newNode.element.compareTo(root.element)>0){
			root.right=newNode;
		}else{
			if(currParent.left!=null && newNode.element.compareTo(currParent.element)<0){
				return recInsert(currParent.left,newNode);
			}else if(currParent.right!=null && newNode.element.compareTo(currParent.element)>0){
				return recInsert(currParent.right,newNode);
			}else if(newNode.element.compareTo(currParent.element)==0){
				return null;
			}
			if (newNode.element.compareTo(currParent.element)<0){
				currParent.left=newNode;
			}else
				currParent.right=newNode;
		}
		return currParent;
	}


	public BST recClone(BSTNode node){
		if(node==null){
			return this;
		}else{
			insert((T)node.element);
			recClone(node.left);
			recClone(node.right);
		}
		return this;
	}

	public void recPrePrint(BSTNode node){
		if(node==null){
			return;
		}else{
			System.out.println(node.element+" ");
			recPrePrint(node.left);
			recPrePrint(node.right);
		}
	}

	public void recPostPrint(BSTNode node){
		if(node==null){
			return;
		}else{
			recPostPrint(node.left);
			recPostPrint(node.right);
			System.out.println(node.element+"");
		}
	}

}