//base binary tree class that implements the binary tree interface
public class TreeBaseBinary implements TreeBinary {

        protected TreeNode root; //root node
        public TreeNode root_copy = root; //copy of root node

        @Override
        public TreeNode getRoot() { //override the method in binary tree interface
            return root;
        }

        @Override
        public String toString() { //print the tree as a string to the terminal
            StringBuilder builder = new StringBuilder();
            //append node to string from the root node
            appendNodeToStringRecursive(getRoot(), builder); 
            return builder.toString();
        }

        private void appendNodeToStringRecursive(TreeNode node, StringBuilder builder) {
            //if node isn't null
            if (node != null) {
                //append the node to the string builder
                appendNodeToString(node, builder);

                //if the left child of the node isn't null
                if (node.left != null) {
                    //append L and then recur to check the left child again
                    builder.append(" L{");
                    appendNodeToStringRecursive(node.left, builder);
                    builder.append('}');
                }

                //if the right child of the node isn't null
                if (node.right != null) {
                    //append R and then recur to check the right child again
                    builder.append(" R{");
                    appendNodeToStringRecursive(node.right, builder);
                    builder.append('}');
                }                
            }

        }

        protected void appendNodeToString(TreeNode node, StringBuilder builder) {
            //append node data Question ID to the string builder if it is not null
            if (node != null)
                builder.append(node.data.getQuestionID());
        }

        public int count_nodes(TreeNode root){
            //count the nodes from the root
            if (root == null){
                //if root is null, return 0
                return 0;
            }

            //recur on the left and right subtree to count the nodes
            //and add it by 1 which is the root node of the tree
            return count_nodes(root.left) + count_nodes(root.right) + 1;
        }


    }

