package stacklist

import (
	"ds/internal/list"
	"ds/internal/list/singlylinkedlist"
	"ds/internal/stack"
)

type StackList struct {
	elements list.List
}

func NewStackListEmp() stack.Stack {
	return &StackList{
		elements: singlylinkedlist.NewSinglyLinkedListEmp(),
	}
}

func NewStackListEle(element interface{}) stack.Stack {
	return &StackList{
		elements: singlylinkedlist.NewSinglyLinkedListEle(element),
	}
}

func (ls *StackList) Push(element interface{}) error {
	ls.elements.AddAtHead(element)
	return nil
}

func (ls *StackList) Pop() interface{} {
	return ls.elements.RemoveAtHead()
}

func (ls *StackList) Size() uint16 {
	return ls.elements.Size()
}

func (ls *StackList) IsEmpty() bool {
	return ls.elements.Size() == 0
}

func (ls *StackList) IsFull() bool {
	return false
}

func (ls *StackList) Peek() interface{} {
	return ls.elements.GetFirstElement()
}
