package ds.tree;

/**
 * Created by ygpark2 on 16. 2. 17.
 */
public class TreeNode<T extends Comparable<T>> {
    private T val;

    private TreeNode left;

    private TreeNode right;

    public TreeNode() {

    }

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setVal(T v) {
        val = v;
    }

    public T getVal() {
        return val;
    }
}
