public class TreeNode {

    //Attributes of the node in the tree
    Question data; //value of the node

    TreeNode left; //left child of the node
    TreeNode right; //right child of the node
    TreeNode parent; //parent of the node

    boolean color; // used in red-black tree to track if the tree is balanced or not

    //constructor
    public TreeNode(Question data) {
        this.data = data;
    }
}