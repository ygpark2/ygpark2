package main

import (
	"fmt"

	"github.com/ygpark2/study/data_structure/go/cmd/tree"
)

func main() {

	// Creating a binary search tree
	test := tree.NewBst()

	// Inserting some data
	test.Insert(4)
	test.Insert(1)
	test.Insert(22)
	test.Insert(699)
	test.Insert(88)

	// Number of nodes in the tree
	fmt.Println(test.Size())

	// Print the tree in-order
	// Traverse the left sub-tree, root, right sub-tree
	tree.showInOrder(test.root)

	// Print the tree pre-order
	// Traverse the root, left sub-tree, right sub-tree
	tree.showPreOrder(test.root)

	// Print the tree post-order
	// Traverse left sub-tree, right sub-tree, root
	tree.showPostOrder(test.root)

	// Removes the item from the tree.
	// Returns true if removed, false if not found.
	test.Delete(4)

	// Returns true if the key exists or return false
	fmt.Println(test.Exists(4))
	fmt.Println(test.Exists(1))
}
