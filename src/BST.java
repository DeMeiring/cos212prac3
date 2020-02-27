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
        BSTNode mRoot = new BSTNode(root.element,root.left,root.right);

        if(root==null)
            return null;
        else{
            mRoot.left = recMirror(root.right);
        }
        return mRoot;
	}


	public BSTNode<T> clone() 
	{
		//Your code goes here
		BST cloneTree = new BST();
         cloneTree.root=recClone(root);
		return cloneTree.root;

	}

	public void printPreorder() 
	{
		//Your code goes here
		recPrePrint(root);
        System.out.println();
	}

        public void printPostorder() 
	{
		//Your code goes here
		recPostPrint(root);
        System.out.println();
	}

	public void insert(T element) 
	{
		//Your code goes here
        if(element == search(element))
            return;
		BSTNode newNode = new BSTNode(element);
		if(root==null){
            root=recInsert(root,newNode);
        }else
		recInsert(root,newNode);
	}

	public boolean deleteByMerge(T element) 
	{
		//Your code goes here
       BSTNode tmp,node,p=root,prev=null;
       while (p!=null && p.element.compareTo(element)!=0){
            prev=p;
            if(p.element.compareTo(element)<0)
                p=p.right;
            else p=p.left;
       }
       node=p;
       if(p!=null && p.element.compareTo(element)==0){
           if(node.right==null)
               node=node.left;
           else if(node.left==null)
               node=node.right;
           else {
               tmp = node.left;
               while (tmp.right != null)
                   tmp = tmp.right;
               tmp.right = node.right;
               node = node.left;
           }
           if(p==root)
               root=node;
           else if(prev.left==p)
               prev.left=node;
           else prev.right=node;
           return true;
           }
        else if(root!=null)
            return false;
        else return false;
       }

	public boolean deleteByCopy(T element) 
	{
		//Your code goes here
        BSTNode node,p=root,prev=null;
        while(p!=null && p.element.compareTo(element)!=0){
            prev=p;
            if(p.element.compareTo(element)<0){
                p=p.right;
            }else
                p=p.left;
        }
        node=p;
        if(p!=null && p.element.compareTo(element)==0){
            if(node.right==null)
                node=node.left;
            else if(node.left==null)
                node=node.right;
            else{
                BSTNode temp=node.left;
                BSTNode previous = node;
                while(temp.right!=null){
                    previous=temp;
                    temp=temp.right;
                }
                node.element=temp.element;

                if(previous==node)
                    previous.left=temp.left;
                else
                    previous.right=temp.left;
            }
            if(p==root)
                root=node;
            else if(prev.left==p)
                prev.left=node;
            else
                prev.right=node;
            return true;
        }
        else if(root!=null)
            return false;
        else
            return false;
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
	public BSTNode recInsert(BSTNode node,BSTNode newNode){
        if(node==null){
            node=newNode;
            return node;
        }else if(newNode.element.compareTo(node.element)<0){
            node.left = recInsert(node.left,newNode);
        }else if(newNode.element.compareTo(node.element)>0){
            node.right = recInsert(node.right,newNode);
        }
        return node;
	}


	public BSTNode recClone(BSTNode node){
		if(node==null)return null;

			BSTNode newNode = new BSTNode(root.element);
			newNode.left=recClone(node.left);
			newNode.right=recClone(node.right);
		return node;
	}

	public void recPrePrint(BSTNode node){
		if(node==null){
			return;
		}else{
			System.out.print(node.element+" ");
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
			System.out.print(node.element+" ");
		}
	}

	public BSTNode recMirror(BSTNode node){
        if(node==null)
            return node;
        else {
            BSTNode left = recMirror(node.left);
            BSTNode right = recMirror(node.right);

            node.left = right;
            node.right = left;
            return node;
        }
    }

    public BSTNode recMerge(BSTNode node, T el){
        if(node==null){
            return node;
        }else{
            if(el.compareTo((T)node.element)<0){
                node.left=recMerge(node.left,el);
                return node;
            }else{
                node.right=recMerge(node.right,el);
                return node;
            }
        }
    }

}