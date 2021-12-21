package queuelist

import (
	"ds/internal/list/singlylinkedlist"
	"reflect"
	"testing"
)

func getEmptyLinearQueue() LinearQueue {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEmp()
	//singlyLinkedList.RemoveAtHead()
	return LinearQueue{
		elements: singlyLinkedList,
	}
}

func getOneElementLongLinearQueue() LinearQueue {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEle(0)
	return LinearQueue{
		elements: singlyLinkedList,
	}
}

func getManyElementLongLinearQueue() LinearQueue {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEle(0)
	for i := 1; i < 10; i++ {
		singlyLinkedList.AddAtHead(i)
	}
	return LinearQueue{
		elements: singlyLinkedList,
	}
}

func TestNewLinearQueueEmp(t *testing.T) {
	want := "*queuelist.LinearQueue"
	subject := NewLinearQueueEmp()
	got := reflect.TypeOf(subject)
	if got.String() != want {
		t.Errorf("It expected %v but got %v", want, got)
	}
}

func TestNewLinearQueueEle(t *testing.T) {
	want := "*queuelist.LinearQueue"
	subject := NewLinearQueueEle(6)
	got := reflect.TypeOf(subject)
	if got.String() != want {
		t.Errorf("It expected %v but got %v", want, got)
	}
}

func TestLinearQueue_Dequeue(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want interface{}
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), nil},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), 0},
		{"ManyElementsLongLinearQueue", getManyElementLongLinearQueue(), 9},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			got := v.data.Dequeue()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestLinearQueue_Enqueue(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want int
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), 4},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), 4},
		{"ManyElementsLongLinearQueue", getManyElementLongLinearQueue(), 4},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			v.data.Enqueue(v.want)
			got := v.data.elements.GetLastElement()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestLinearQueue_IsEmpty(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want bool
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), true},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), false},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			got := v.data.IsEmpty()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestLinearQueue_IsFull(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want bool
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), false},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), false},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			got := v.data.IsFull()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestLinearQueue_Size(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want uint16
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), uint16(0)},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), uint16(1)},
		{"ManyElementsLongLinearQueue", getManyElementLongLinearQueue(), uint16(10)},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			got := v.data.Size()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestLinearQueue_Peek(t *testing.T) {
	dataTable := []struct {
		name string
		data LinearQueue
		want interface{}
	}{
		{"EmptyLinearQueue", getEmptyLinearQueue(), nil},
		{"OneElementLongLinearQueue", getOneElementLongLinearQueue(), 0},
		{"ManyElementsLongLinearQueue", getManyElementLongLinearQueue(), 9},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			v.data.Peek()
			got := v.data.Peek()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}
