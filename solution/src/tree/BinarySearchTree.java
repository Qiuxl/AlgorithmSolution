package tree;

public class BinarySearchTree {

    // 寻找二叉搜索树中第k大的数
    // Root of BST 
    TreeNode root;

    // Constructor 
    BinarySearchTree()
    {
        root = null;
    }

    // function to insert TreeNodes 
    public void insert(int val)
    {
        this.root = this.insertRec(this.root, val);
    }

    /* A utility function to insert a new TreeNode  
    with given key in BST */
    TreeNode insertRec(TreeNode TreeNode, int val)
    {
        /* If the tree is empty, return a new TreeNode */
        if (TreeNode == null) {
            this.root = new TreeNode(val);
            return this.root;
        }

        if (val == TreeNode.val) {
            return TreeNode;
        }

        /* Otherwise, recur down the tree */
        if (val < TreeNode.val) {
            TreeNode.left = this.insertRec(TreeNode.left, val);
        } else {
            TreeNode.right = this.insertRec(TreeNode.right, val);
        }
        return TreeNode;
    }

    // class that stores the value of count 
    public class count {
        int c = 0;
    }

    // utility function to find kth largest no in  
    // a given tree 
    void kthLargestUtil(TreeNode TreeNode, int k, count C)
    {
        // Base cases, the second condition is important to 
        // avoid unnecessary recursive calls 
        if (TreeNode == null || C.c >= k)
            return;

        // Follow reverse inorder traversal so that the 
        // largest element is visited first 
        this.kthLargestUtil(TreeNode.right, k, C);

        // Increment count of visited TreeNodes 
        C.c++;

        // If c becomes k now, then this is the k'th largest  
        if (C.c == k) {
            System.out.println(k + "th largest element is " +
                    TreeNode.val);
            return;
        }

        // Recur for left subtree 
        this.kthLargestUtil(TreeNode.left, k, C);
    }

    // Method to find the kth largest no in given BST 
    void kthLargest(int k)
    {
        count c = new count(); // object of class count 
        this.kthLargestUtil(this.root, k, c);
    }

    // Driver function 
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree(); 
          
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        for (int i = 1; i <= 7; i++) {
            tree.kthLargest(i);
        }
    }
} 