package fcu.debug;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LCA {

    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        // 如果根節點為空，則返回空
        if (root == null) {
            return null;
        }

        // 如果 p 和 q 都比 root 小，則最小公共祖先在左子樹
        if (p.val < root.val && q.val < root.val) {
            return findLCA(root.left, p, q);
        }

        // 如果 p 和 q 都比 root 大，則最小公共祖先在右子樹
        if (p.val > root.val && q.val > root.val) {
            return findLCA(root.right, p, q);
        }

        // 如果 p 和 q 分別位於 root 的左右兩側，則 root 是最小公共祖先
        return root;
    }

    public static void main(String[] args) {
        // 建立二叉搜尋樹
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        // 測試範例：查找節點 2 和 8 的最小公共祖先
        LCA lca = new LCA();
        TreeNode ancestor = lca.findLCA(root, root.left, root.right);

        System.out.println("The LCA of node 2 and node 8 is: " + ancestor.val);
    }
}
