package tree

import (
	"fmt"
)

// Print the tree in-order
// Traverse the left sub-tree, root, right sub-tree
func ShowInOrder(root *Node) {
	if root != nil {
		ShowInOrder(root.left)
		fmt.Println(root.value)
		ShowInOrder(root.right)
	}
}

// Print the tree pre-order
// Traverse the root, left sub-tree, right sub-tree
func ShowPreOrder(root *Node) {
	if root != nil {
		fmt.Println(root.value)
		ShowInOrder(root.left)
		ShowInOrder(root.right)
	}
}

// Print the tree post-order
// Traverse left sub-tree, right sub-tree, root
func ShowPostOrder(root *Node) {
	if root != nil {
		fmt.Println(root.value)
		ShowInOrder(root.left)
		ShowInOrder(root.right)
	}
}
