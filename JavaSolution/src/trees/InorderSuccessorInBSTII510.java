/**
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.

 

Example 1:


Input: 
root = {"$id":"1","left":{"$id":"2","left":null,"parent":{"$ref":"1"},"right":null,"val":1},"parent":null,"right":{"$id":"3","left":null,"parent":{"$ref":"1"},"right":null,"val":3},"val":2}
p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of Node type.
Example 2:


Input: 
root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":1},"parent":{"$ref":"2"},"right":null,"val":2},"parent":{"$ref":"1"},"right":{"$id":"5","left":null,"parent":{"$ref":"2"},"right":null,"val":4},"val":3},"parent":null,"right":{"$id":"6","left":null,"parent":{"$ref":"1"},"right":null,"val":6},"val":5}
p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
Example 3:


Input: 
root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
p = 15
Output: 17
Example 4:


Input: 
root = {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":{"$id":"4","left":null,"parent":{"$ref":"3"},"right":null,"val":2},"parent":{"$ref":"2"},"right":{"$id":"5","left":null,"parent":{"$ref":"3"},"right":null,"val":4},"val":3},"parent":{"$ref":"1"},"right":{"$id":"6","left":null,"parent":{"$ref":"2"},"right":{"$id":"7","left":{"$id":"8","left":null,"parent":{"$ref":"7"},"right":null,"val":9},"parent":{"$ref":"6"},"right":null,"val":13},"val":7},"val":6},"parent":null,"right":{"$id":"9","left":{"$id":"10","left":null,"parent":{"$ref":"9"},"right":null,"val":17},"parent":{"$ref":"1"},"right":{"$id":"11","left":null,"parent":{"$ref":"9"},"right":null,"val":20},"val":18},"val":15}
p = 13
Output: 15
 

Note:

If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.
Remember that we are using the Node type instead of TreeNode type so their string representation are different.
*/


// 不用看value，根据bst的性质来处理。有三种情况，包括两种处理方法，找右子树的最左node，或找当前node的第一个在右侧的parent
class InorderSuccessorInBSTII510 {
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node parent;
	};

	public Node inorderSuccessor(Node x) {
		if(x == null) return null;

		if(x.parent == null) {
        	return findLeftMostChildOnRight(x);
		}
		else {
			if(x.right != null) {
				return findLeftMostChildOnRight(x);
			}
			else {
				// Find first parent on right
				Node firstParentOnRight = findFirstParentOnRight(x);
				return firstParentOnRight;
			}			
		}

    }

    private Node findLeftMostChildOnRight(Node x) {
    	Node curr = x.right;
    	Node prev = curr;
    	while(curr != null) {
    		prev = curr;
    		curr = curr.left;
    	}
    	return prev;
    }

    private Node findFirstParentOnRight(Node x) {
    	if(x == null) return null;
    	
    	Node curr = x;
    	Node p = x.parent;
    	while(p != null) {
    		if(p.left == curr) {
    			return p;
    		}
    		curr = p;
    		p = p.parent;
    	}

    	return null;
    }


   
}