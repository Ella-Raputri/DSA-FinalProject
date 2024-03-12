public interface TreeBinarySearch extends TreeBinary {

    /**
     * Searches for a node with the given search key.
     *
     * @param key the search key
     * @return the node or <code>null</code> if no node with the given search key exists
     */
    TreeNode searchNode(int key);

    /**
     * Inserts a node with the given key.
     *
     * @param key the key of the node to be inserted
     */
    void insertNode(Question key);

    /**
     * Deletes the node with the given key.
     *
     * @param key the key of the node to be deleted
     */
    void deleteNode(String key_id);
}
