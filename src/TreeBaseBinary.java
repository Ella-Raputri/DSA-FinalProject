public class TreeBaseBinary implements TreeBinary {

        protected TreeNode root;
        public TreeNode root_copy = root;

        @Override
        public TreeNode getRoot() {
            return root;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            appendNodeToStringRecursive(getRoot(), builder);
            return builder.toString();
        }

        private void appendNodeToStringRecursive(TreeNode node, StringBuilder builder) {
            appendNodeToString(node, builder);
            if (node.left != null) {
                builder.append(" L{");
                appendNodeToStringRecursive(node.left, builder);
                builder.append('}');
            }
            if (node.right != null) {
                builder.append(" R{");
                appendNodeToStringRecursive(node.right, builder);
                builder.append('}');
            }
        }

        protected void appendNodeToString(TreeNode node, StringBuilder builder) {
            builder.append(node.data.getQuestionNumber());
        }

        // public void inOrderTrav(TreeNode  curr, int count[]) {
        //     if (curr == null)
        //         return;

        //     count[0]++;
        //     inOrderTrav(curr . left, count);
        //     inOrderTrav(curr . right, count);
        // }

        public int count_nodes(TreeNode root){
            if (root == null){
                return 0;
            }
            return count_nodes(root.left) + count_nodes(root.right) + 1;
        }
    }

