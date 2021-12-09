package ds.tree;

import java.util.Objects;

/**
 * Created by ygpark2 on 16. 2. 17.
 */
public class Tree<T extends Comparable<T>> {

  TreeNode<T> treeNode = new TreeNode<>();

  public Tree(T val) {
    treeNode.setVal(val);
  }

  public void insert(T data) {
    treeNode = insertNode(treeNode, data);
  }

  private TreeNode<T> insertNode(TreeNode tn, T data) {

    if (Objects.isNull(tn)) {
      return new TreeNode<>(data);
    }

    if (tn.getVal().compareTo(data) == 0) {
      return tn;
    }

    if(tn.getVal().compareTo(data) > 0) {
      tn.setRight(insertNode(tn.getRight(), data));
    } else {
      tn.setLeft(insertNode(tn.getLeft(), data));
    }
    return tn;
  }

  public boolean search(T toSearch) {
    return search(treeNode, toSearch);
  }

  private boolean search(TreeNode<T> tn, T schVal) {
    if (Objects.isNull(tn)) {
      return false;
    }

    if (tn.getVal().compareTo(schVal) == 0) return true;
    else
        if (tn.getVal().compareTo(schVal) > 0)
          return search(tn.getRight(), schVal);
        else
          return search(tn.getLeft(), schVal);
  }

  public void delete(T delVal) {
    treeNode = delete(treeNode, delVal);
  }

  private TreeNode<T> delete(TreeNode<T> tn, T delVal) {
    if (Objects.isNull(tn)) throw new RuntimeException("cannot delete!");
    else {
      if (tn.getVal().compareTo(delVal) > 0)
        tn.setRight(delete(tn.getRight(), delVal));
      else if (tn.getVal().compareTo(delVal) < 0)
        tn.setLeft(delete(tn.getLeft(), delVal));
      else {
        if (Objects.isNull(tn.getRight()))
          return tn.getLeft();
        else if (Objects.isNull(tn.getLeft()))
          return tn.getRight();
        else {
          tn.setVal((T) retrieveData(tn.getLeft()));
          tn.setLeft(delete(tn.getLeft(), delVal));
        }
      }
    }
    return tn;
  }

  private T retrieveData(TreeNode<T> tn) {
    while (!Objects.isNull(tn.getRight())) tn = tn.getRight();

    return tn.getVal();
  }


  public void preOrderTraversal() {
    preOrderHelper(treeNode);
  }

  private void preOrderHelper(TreeNode<T> preNode)
  {
    if (preNode != null)
    {
      System.out.print(preNode.getVal() + " ");
      preOrderHelper(preNode.getLeft());
      preOrderHelper(preNode.getRight());
    }
  }

  public void postOrderTraversal() {
    postOrderHelper(treeNode);
  }

  private void postOrderHelper(TreeNode<T> preNode)
  {
    if (preNode != null)
    {
      preOrderHelper(preNode.getLeft());
      preOrderHelper(preNode.getRight());
      System.out.print(preNode.getVal() + " ");
    }
  }

  public void inOrderTraversal()
  {
    inOrderHelper(treeNode);
  }

  private void inOrderHelper(TreeNode<T> inNode)
  {
    if (inNode != null)
    {
      inOrderHelper(inNode.getLeft());
      System.out.print(inNode.getVal() + " ");
      inOrderHelper(inNode.getRight());
    }
  }

  public int countLeaves() {
    return countLeaves(treeNode);
  }

  private int countLeaves(TreeNode<T> tn) {
    if (Objects.isNull(tn)) return 0;
    else
      if (Objects.isNull(tn.getLeft()) && Objects.isNull(tn.getRight())) return 1;
      else return countLeaves(tn.getLeft()) + countLeaves(tn.getRight());
  }

  public int width() {
    int max = 0;
    for(int k = 0; k <= height(); k++) {
      int tmp = width(treeNode, k);
      if(tmp > max) max = tmp;
    }
    return max;
  }

  public int width(TreeNode<T> tn, int depth) {
    if (Objects.isNull(tn)) return 0;
    else
      if (depth == 0) return 1;
      else
        return width(tn.getLeft(), depth - 1) + width(tn.getRight(), depth - 1);
  }

  public int height() {
    return height(treeNode);
  }

  private int height(TreeNode<T> tn) {
    if (Objects.isNull(tn))
      return 0;
    else
      return 1 + Math.max(height(tn.getLeft()), height(tn.getRight()));
  }

  public int diameter() {
    return diameter(treeNode);
  }

  private int diameter(TreeNode<T> tn) {
    if (Objects.isNull(tn))
      return 0;

    int len1 = height(tn.getLeft()) + height(tn.getRight()) + 3;

    int len2 = Math.max(diameter(tn.getLeft()), diameter(tn.getRight()));

    return Math.max(len1, len2);
  }
}
