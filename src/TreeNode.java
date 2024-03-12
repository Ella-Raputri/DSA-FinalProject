public class TreeNode {

    // also called "value" in a binary tree
    // also called "key" in a binary search tree
    Question data;

    TreeNode left;
    TreeNode right;
    TreeNode parent; // used in SimpleBinaryTree + red-black tree

    int height; // used in AVL tree
    boolean color; // used in red-black tree

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to store in the node
     */
    public TreeNode(Question data) {
        this.data = data;
    }

    public Question getData() {
        return data;
    }
}