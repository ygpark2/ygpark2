package ds.tree;

public class BinaryTree {

  public TreeNode<Integer> root;

  public void makeTree(int[] a) {
    root = makeTreeR(a, 0, a.length - 1);
  }

  public TreeNode<Integer> makeTreeR(int[] a, int start, int end) {
    if (start > end) return null;
    int mid = (start + end) / 2;
    TreeNode<Integer> node = new TreeNode<>(a[mid]);
    node.setLeft(makeTreeR(a, start, mid - 1));
    node.setRight(makeTreeR(a, mid + 1, end));
    return node;
  }

  public void searchBtree(TreeNode<Integer> n, Integer find) {
    if (n.getVal() < find) {
      System.out.println("Data is smaller than " + n.getVal());
      searchBtree(n.getRight(), find);
    } else if (n.getVal() > find) {
      System.out.println("Data is larger than " + n.getVal());
      searchBtree(n.getLeft(), find);
    } else {
      System.out.println("Found !!! => " + find);
    }

  }

}
