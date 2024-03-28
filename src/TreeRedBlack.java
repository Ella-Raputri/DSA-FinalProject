import java.util.LinkedList;
/**
 * A red-black tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class TreeRedBlack extends TreeBaseBinary implements TreeBinarySearch {
    static final boolean RED = false;
    static final boolean BLACK = true;

    @Override
    public TreeNode searchNode(String key) {
        int key_number = Integer.parseInt(key);
        TreeNode node = root;
        while (node != null) {
            if (key_number == Integer.parseInt(node.data.getQuestionID())) {
                return node;
            } else if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    // -- Insertion ----------------------------------------------------------------------------------
    @Override
    public void insertNode(Question key) {
        int key_number = Integer.parseInt(key.getQuestionID());
        TreeNode node = root;
        TreeNode parent = null;

        // Traverse the tree to the left or right depending on the key
        while (node != null) {
            parent = node;
            if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            } else if (key_number > Integer.parseInt(node.data.getQuestionID())) {
                node = node.right;
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
            }
        }

        // Insert new node
        TreeNode newNode = new TreeNode(key);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (key_number < Integer.parseInt(parent.data.getQuestionID())) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        fixRedBlackPropertiesAfterInsert(newNode);
    }

    @SuppressWarnings("squid:S125") // Ignore SonarCloud complains about commented code line 70.
    private void fixRedBlackPropertiesAfterInsert(TreeNode node) {
        TreeNode parent = node.parent;

        // Case 1: Parent is null, we've reached the root, the end of the recursion
        if (parent == null) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            node.color = BLACK;
            return;
        }

        // Parent is black --> nothing to do
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
        TreeNode grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    // -- Deletion -----------------------------------------------------------------------------------
    @Override
    public void deleteNode(String key_id) {
        int key_number = Integer.parseInt(key_id);
        TreeNode node = root;

        // Find the node to be deleted
        while (node != null && !node.data.getQuestionID().equals(key_id)) {
            // Traverse the tree to the left or right depending on the key
            if (key_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // Node not found?
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
        while (node.left != null) {
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
        TreeNode parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private boolean isBlack(TreeNode node) {
        return node == null || node.color == BLACK;
    }

    private static class NilNode extends TreeNode {
        private NilNode() {
            super(new Question("0","0"));
            this.color = BLACK;
        }
    }

    // -- Helpers for insertion and deletion ---------------------------------------------------------

    private void rotateRight(TreeNode node) {
        TreeNode parent = node.parent;
        TreeNode leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(TreeNode node) {
        TreeNode parent = node.parent;
        TreeNode rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    private void replaceParentsChild(TreeNode parent, TreeNode oldChild, TreeNode newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    // -- For toString() -----------------------------------------------------------------------------

    @Override
    protected void appendNodeToString(TreeNode node, StringBuilder builder) {
        if (node != null)
            builder.append(node.data.getQuestionID()).append(node.color == RED ? "[R]" : "[B]");
    }

    public int getQuestionNumberfromNode(String questionID) {
        int id_number = Integer.parseInt(questionID);
        TreeNode node = root;

        while (node != null && !node.data.getQuestionID().equals(questionID)) {
            // Traverse the tree to the left or right depending on the key
            if (id_number < Integer.parseInt(node.data.getQuestionID())) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        try {
            int number = node.data.getQuestionNumber();
        } catch (Exception e) {
            System.out.println("Error");
            return -1;
        }

        return node.data.getQuestionNumber();       
    }


    public TreeNode searchNodeBasedonNumber(TreeNode rootNode, int key) {
        if (rootNode == null) 
            return null; 

        if (rootNode.data.getQuestionNumber() == key) 
            return rootNode; 
    
        // then recur on left subtree /
        TreeNode res1 = searchNodeBasedonNumber(rootNode.left, key); 
        // node found, no need to look further
        if(res1 != null) 
            return res1; 
    
        // node is not found in left, 
        // so recur on right subtree /
        TreeNode res2 = searchNodeBasedonNumber(rootNode.right, key); 
        return res2;
    }


    public LinkedList<TreeNode> searchNodeString(TreeNode rootNode, String str, LinkedList<TreeNode> res) {
        if (rootNode != null){
            if (rootNode.data.getQuestion().contains(str) || rootNode.data.getCorrectAnswer().contains(str)){
                res.add(rootNode);
            }
            
            res = searchNodeString(rootNode.left, str, res); 
            res = searchNodeString(rootNode.right, str, res);  
        }

        return res;
    }

    public void printFullNode(TreeNode rootNode, int count){  
        for (int i = 0; i < count; i++){
            TreeNode result = searchNodeBasedonNumber(rootNode,i+1);  

            if (result == null){ 
                continue;
            }else{
                Question q1 = searchNodeBasedonNumber(rootNode, i+1).data;
                System.out.println("Question " + (i+1));
                System.out.println("Question ID: "+ q1.getQuestionID());
                System.out.println("Question: "+ q1.getQuestion());
                System.out.println("Answer: "+ q1.getCorrectAnswer());
                System.out.println();
            }
        }
         
    }

    public void inOrder(TreeNode root){  
        if (root == null) {
            return;
        }   

        inOrder(root.left);
        System.out.print(" "+root.data.getQuestionID()+" "+root.data.getQuestionNumber());
        inOrder(root.right);
    } 

    //for forward case, set some numbers backward
    public void inOrderForward(TreeNode root, int newNum, int oldNum){  
        if (root == null) {
            return;
        }   

        inOrderForward(root.left, newNum, oldNum);
        int a = root.data.getQuestionNumber();
        if (a < oldNum && a > newNum) {
           root.data.setQuestionNumber(a+1); 
        }
        inOrderForward(root.right, newNum, oldNum);
    }


    //for backward case, set some numbers forward
    public void inOrderBackward(TreeNode root, int newNum, int oldNum){  
        if (root == null) {
            return;
        }   

        inOrderBackward(root.left, newNum, oldNum);
        int a = root.data.getQuestionNumber();
        if (a > oldNum && a < newNum) {
           root.data.setQuestionNumber(a-1); 
        }
        inOrderBackward(root.right, newNum, oldNum);
    }



}
