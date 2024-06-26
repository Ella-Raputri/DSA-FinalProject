//import Java APIs
import java.util.Collections;
import java.util.LinkedList;
/**
 * A red-black tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class TreeRedBlack extends TreeBaseBinary implements TreeBinarySearch {
    //node's color
    static final boolean RED = false;
    static final boolean BLACK = true;

    @Override //override the searchNode method in TreeBinarySearch interface
    public TreeNode searchNode(String key) {
        //parse the ID into integer
        //because the node is arranged based on their ID
        int key_number = 0;
        TreeNode node = root;

        if (isNumeric(key)) {
            key_number = Integer.parseInt(key);

            //check starting from the root node
            while (node != null) {
                //if the searched ID is equal to the node, then return the node
                if (key_number == Integer.parseInt(node.data.getQuestionID())) {
                    return node;

                //if the searched ID is lesser than the one in the node, then the searched node
                //can be searched from the node left child
                } else if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                    node = node.left;

                //if the searched ID is greater than the one in the node, then the searched node
                //can be searched from the node right child
                } else {
                    node = node.right;
                }
            }
        }
        
        return null;
    }

    
    public boolean isNumeric(String strNum) {
        //to check whether some string is numeric
        if (strNum == null) {
            //if the string is null, then return false
            return false;
        }
        //if not null, then try to parse the string to integer
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            //if exception happens, then return false
            return false;
        }
        return true; //if not, return true
    }

    public int partition(LinkedList<TreeNode> arr, int low, int high){
        // Choosing the pivot
        int pivot = arr.get(high).data.getQuestionNumber();

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (arr.get(j).data.getQuestionNumber() < pivot) { 
                // Increment index of smaller element
                i++;
                Collections.swap(arr, i, j);
            }
        }
        Collections.swap(arr, i + 1, high);
        return (i + 1);
    }
    
    public void quickSort(LinkedList<TreeNode> arr, int low, int high){
        //perform quick sort by first partition it first
        if (low < high) {
            int pi = partition(arr, low, high);

            //then recur on the range before and range after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    // ----- Insertion ------
    @Override //override the insertNode method in TreeBinarySearch interface
    public void insertNode(Question key) {
        //change the ID number from string to integer
        int key_number = Integer.parseInt(key.getQuestionID());
        TreeNode node = root;
        TreeNode parent = null;

        // Traverse the tree to the left or right depending on the key
        while (node != null) {
            parent = node;
            //if the inputted ID is lesser, then traverse to left
            if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;

            //if the inputted ID is greater, then traverse to right
            } else if (key_number > Integer.parseInt(node.data.getQuestionID())) {
                node = node.right;

            //if the inputted ID already exists, throw exception
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
            }
        }

        // Insert new node
        TreeNode newNode = new TreeNode(key);
        newNode.color = RED; //default color will be red, before we fix it later
        
        //if the parent node is null, then the new node will be the root
        if (parent == null) {
            root = newNode;
        
        //if the inserted ID is lesser than the parent, traverse and insert to left
        } else if (key_number < Integer.parseInt(parent.data.getQuestionID())) {
            parent.left = newNode;
        //else traverse and insert to right
        } else {
            parent.right = newNode;
        }
        //set the parent node of the new node because it is one of the attribute
        newNode.parent = parent;

        //recolor and rotate the red black tree to rebalance it
        fixRedBlackPropertiesAfterInsert(newNode);
    }

    private void fixRedBlackPropertiesAfterInsert(TreeNode node) {
        TreeNode parent = node.parent;

        // Case 1: Parent is null, we've reached the root, the end of the recursion
        if (parent == null) {
            // color the root node to be black
            node.color = BLACK;
            return;
        }

        // Parent is black, then return
        if (parent.color == BLACK) {
            return;
        }

        // From here on, parent is red
        TreeNode grandparent = parent.parent;

        // Case 2:
        // Not having a grandparent means that parent is the root. If we enforce black roots
        // (rule 2), grandparent will never be null, and the following if-then block can be
        // removed.
        if (grandparent == null) {
            // As this method is only called on red nodes (either on newly inserted ones - or -
            // recursively on red grandparents), all we have to do is to recolor the root black.
            parent.color = BLACK;
            return;
        }

        // Get the uncle (maybe null/nil, in which case its color is BLACK)
        TreeNode uncle = getUncle(parent);

        // Case 3: Uncle is red -> recolor parent, grandparent and uncle
        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;

            // Call recursively for grandparent, which is now red.
            // It might be root or have a red parent, in which case we need to fix more...
            fixRedBlackPropertiesAfterInsert(grandparent);
        }

        // Note on performance:
        // It would be faster to do the uncle color check within the following code. This way
        // we would avoid checking the grandparent-parent direction twice (once in getUncle()
        // and once in the following else-if). But for better understanding of the code,
        // I left the uncle color check as a separate step.

        // Parent is left child of grandparent
        else if (parent == grandparent.left) {
            // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
            if (node == parent.right) {
                rotateLeft(parent);

                // Let "parent" point to the new root node of the rotated subtree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
            rotateRight(grandparent);

            // Recolor original parent and grandparent
            parent.color = BLACK;
            grandparent.color = RED;
        }

        // Parent is right child of grandparent
        else {
            // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
            if (node == parent.left) {
                rotateRight(parent);

                // Let "parent" point to the new root node of the rotated subtree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
            rotateLeft(grandparent);

            // Recolor original parent and grandparent
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }

    private TreeNode getUncle(TreeNode parent) {
        //get the uncle node by first accessing the grandparent node
        TreeNode grandparent = parent.parent;
        //if the left child is the parent, then the uncle is the right child of grandparent
        if (grandparent.left == parent) {
            return grandparent.right;
        
        //if the right child is the parent, then the uncle is the left child of grandparent
        } else if (grandparent.right == parent) {
            return grandparent.left;
        
        //invalid 
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }


    // ------ Deletion ------
    @Override
    public void deleteNode(String key_id) {
        //parse the ID to be integer
        int key_number = Integer.parseInt(key_id);
        TreeNode node = root;

        // Find the node to be deleted
        while (node != null && !node.data.getQuestionID().equals(key_id)) {
            // Traverse the tree to the left or right depending on the key
            //if the key is lesser then traverse to left
            if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            //if the key is greater then traverse to right
            } else {
                node = node.right;
            }
        }

        //if node is not found, then print the error message
        if (node == null) {
            System.out.println("Question not found");
            return;
        }

        // At this point, "node" is the node to be deleted

        // In this variable, we'll store the node at which we're going to start to fix the R-B
        // properties after deleting a node.
        TreeNode movedUpNode;
        boolean deletedNodeColor;

        // Node has zero or one child
        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        }

        // Node has two children
        else {
            // Find minimum node of right subtree ("inorder successor" of current node)
            TreeNode inOrderSuccessor = findMinimum(node.right);

            // Copy inorder successor's data to current node (keep its color!)
            node.data = inOrderSuccessor.data;

            // Delete inorder successor just as we would delete a node with 0 or 1 child
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }

        if (deletedNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);

            // Remove the temporary NIL node
            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }
    }

    private TreeNode deleteNodeWithZeroOrOneChild(TreeNode node) {
        // Node has ONLY a left child --> replace by its left child
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left; // moved-up node
        }

        // Node has ONLY a right child --> replace by its right child
        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right; // moved-up node
        }

        // Node has no children -->
        // * node is red --> just remove it
        // * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
        else {
            TreeNode newChild = node.color == BLACK ? new NilNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }

    private TreeNode findMinimum(TreeNode node) {
        //find the node with the minimum ID value
        while (node.left != null) {
            //find the leftmost leaf node
            node = node.left;
        }
        return node;
    }

    private void fixRedBlackPropertiesAfterDelete(TreeNode node) {
        // Case 1: Examined node is root, end of recursion
        if (node == root) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            node.color = BLACK;
            return;
        }

        TreeNode sibling = getSibling(node);

        // Case 2: Red sibling
        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
        }

        // Cases 3+4: Black sibling with two black children
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            // Case 3: Black sibling with two black children + red parent
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }

            // Case 4: Black sibling with two black children + black parent
            else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }

        // Case 5+6: Black sibling with at least one red child
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private void handleRedSibling(TreeNode node, TreeNode sibling) {
        // Recolor...
        sibling.color = BLACK;
        node.parent.color = RED;

        // ... and rotate
        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(TreeNode node, TreeNode sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;

        // Case 5: Black sibling with at least one red child + "outer nephew" is black
        // --> Recolor sibling and its child, and rotate around sibling
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }

        // Fall-through to case 6...

        // Case 6: Black sibling with at least one red child + "outer nephew" is red
        // --> Recolor sibling + parent + sibling's child, and rotate around parent
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private TreeNode getSibling(TreeNode node) {
        //get sibling of the node
        TreeNode parent = node.parent;
        //if node is the left child of its parent, its sibling is the right child
        if (node == parent.left) {
            return parent.right;
        //if node is the right child of its parent, its sibling is the left child
        } else if (node == parent.right) {
            return parent.left;
        //invalid
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private boolean isBlack(TreeNode node) {
        return node == null || node.color == BLACK;
    }

    private static class NilNode extends TreeNode {
        //class NilNode is the utmost leaf node in the tree
        private NilNode() {
            super(new Question("0","0"));
            this.color = BLACK;
        }
    }


    // ----- Helpers for insertion and deletion ------
    private void rotateRight(TreeNode node) {
        //access the parent node and the left child of the node
        TreeNode parent = node.parent;
        TreeNode leftChild = node.left;

        //set the left child of the node to be its original leftchild's right child
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        //set node to be the right child of the left child
        leftChild.right = node;
        //set the original left child of the node to be its parent
        node.parent = leftChild;

        //the leftChild node will now has parent of the parent node
        //(originally its grandparent)
        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(TreeNode node) {
        //access the parent and the right child of the node
        TreeNode parent = node.parent;
        TreeNode rightChild = node.right;

        //set the right child of the node to be its original rightchild's left child
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        //set node to be the left child of the right child
        rightChild.left = node;
        //set the original right child of the node to be its parent
        node.parent = rightChild;

        //the rightChild node will now has parent of the parent node
        //(originally its grandparent)
        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        //if parent is null, then the new child will be the root node
        if (parent == null) {
            root = newChild;
        //if old child is the left child of the parent
        //then the new child will now be the left child of the parent
        } else if (parent.left == oldChild) {
            parent.left = newChild;

        //if old child is the right child of the parent
        //then the new child will now be the right child of the parent
        } else if (parent.right == oldChild) {
            parent.right = newChild;

        //invalid node
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        //if new child is not null, then the parent node will be the new child parent
        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    // ----- For toString() --------

    @Override //override the method that is inheritted from TreeBaseBinary
    protected void appendNodeToString(TreeNode node, StringBuilder builder) {
        //if the node is not null, then append the node Question ID with the color R or B to the string builder
        if (node != null)
            builder.append(node.data.getQuestionID()).append(node.color == RED ? "[R]" : "[B]");
    }


    //------- Helper method for search, print, and delete Questions ---------

    public int getQuestionNumberfromNode(String questionID) {
        //parse the ID number into integer
        int id_number = Integer.parseInt(questionID);
        TreeNode node = root;

        while (node != null && !node.data.getQuestionID().equals(questionID)) {
            // Traverse the tree to the left if the ID number is lesser than the one in the node
            if (id_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            // Traverse the tree to the right if the ID number is greater than the one in the node
            } else {
                node = node.right;
            }
        }

        try {
            //if there exists such a node, get the question number
            int number = node.data.getQuestionNumber();
        } catch (Exception e) {
            //print error
            System.out.println("Error");
            return -1;
        }

        //return the question number
        return node.data.getQuestionNumber();       
    }


    public TreeNode searchNodeBasedonNumber(TreeNode rootNode, int key, TreeNode result) {
        //if rootnode is not null
        if (rootNode != null){ 
            //if the question number in the rootnode equals to the key number
            if (rootNode.data.getQuestionNumber() == key){ 
                //the result will be the rootnode
                result = rootNode;
            } 
            //else, traverse the tree to left
            result = searchNodeBasedonNumber(rootNode.left, key, result); 
            //or to the right to search every node that matches the question number
            result = searchNodeBasedonNumber(rootNode.right, key, result); 
        }
        //return the result node
        return result;
    }


    public LinkedList<TreeNode> searchNodeString(TreeNode rootNode, String str, LinkedList<TreeNode> res) {
        //if rootnode is not null
        if (rootNode != null){
            //if the rootnode question or answer contains the searched string
            if (rootNode.data.getQuestion().contains(str) || rootNode.data.getCorrectAnswer().contains(str)){
                //add the rootnode to the result linked list
                res.add(rootNode);
            }
            //after that, traverse the tree to the left and search the string 
            res = searchNodeString(rootNode.left, str, res); 
            //or traverse the tree to the right
            //append all the nodes that matches the criteria to the linked list
            res = searchNodeString(rootNode.right, str, res);  
        }

        return res; //return the linked list
    }

    public void printFullNode(TreeNode rootNode, int count){  
        for (int i = 0; i < count; i++){ //to print in order
            TreeNode result = null; 
            //search the node based on number 
            result = searchNodeBasedonNumber(rootNode,i+1, result);  

            //if the result is null, then print nothing
            if (result == null){ 
                continue;
            //if there exists a result, then print the result information
            }else{
                Question q1 = result.data;
                System.out.println("Question " + (i+1));
                System.out.println("Question ID: "+ q1.getQuestionID());
                System.out.println("Question: "+ q1.getQuestion());
                System.out.println("Answer: "+ q1.getCorrectAnswer());
                System.out.println();
            }
        }
         
    }


    //for forward case, set some numbers backward
    public void inOrderForward(TreeNode root, int newNum, int oldNum){  
        //if root is null, then we dont have to do anything
        if (root == null) {
            return;
        }   

        //if not, then set backward all nodes in the left subtree
        inOrderForward(root.left, newNum, oldNum);
        
        int a = root.data.getQuestionNumber();
        //if the node's question number is greater than the new number and less than the old number
        if (a < oldNum && a > newNum) {
           root.data.setQuestionNumber(a+1); //set the node question number backward by 1
        }
        
        //set backward all nodes in the right subtree
        inOrderForward(root.right, newNum, oldNum);
    }


    //for backward case, set some numbers forward
    public void inOrderBackward(TreeNode root, int newNum, int oldNum){  
        //if root is null, then we dont have to do anything
        if (root == null) {
            return;
        }   

        //if not, then set forward all nodes in the left subtree
        inOrderBackward(root.left, newNum, oldNum);

        int a = root.data.getQuestionNumber();
        //if the node's question number is greater than the old number and less than the new number
        if (a > oldNum && a < newNum) {
           root.data.setQuestionNumber(a-1); //set the node question number forward by 1
        }

        //set backward all nodes in the right subtree
        inOrderBackward(root.right, newNum, oldNum);
    }



}
