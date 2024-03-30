//binary search tree interface which inherits from binary tree
public interface TreeBinarySearch extends TreeBinary {

    //search nodes in the tree based on the Question ID
    TreeNode searchNode(String key_id);

    //insert Question as data in a node
    void insertNode(Question question);

    //delete a node based on the Question ID
    void deleteNode(String key_id);
}
