package stacklist

import (
	"ds/internal/list/singlylinkedlist"
	"reflect"
	"testing"
)

func getEmptyStackList() StackList {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEle(0)
	singlyLinkedList.RemoveAtHead()
	return StackList{
		elements: singlyLinkedList,
	}
}

func getOneElementLongStackList() StackList {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEle(0)
	return StackList{
		elements: singlyLinkedList,
	}
}

func getManyElementLongStackList() StackList {
	singlyLinkedList := singlylinkedlist.NewSinglyLinkedListEle(0)
	for i := 1; i < 10; i++ {
		singlyLinkedList.AddAtHead(i)
	}
	return StackList{
		elements: singlyLinkedList,
	}
}

func TestNewStackListEmp(t *testing.T) {
	want := "*stacklist.StackList"
	subject := NewStackListEmp()
	got := reflect.TypeOf(subject)
	if got.String() != want {
		t.Errorf("It expected %v but got %v", want, got)
	}
}

func TestNewStackListEle(t *testing.T) {
	want := "*stacklist.StackList"
	subject := NewStackListEle(5)
	got := reflect.TypeOf(subject)
	if got.String() != want {
		t.Errorf("It expected %v but got %v", want, got)
	}
}

func TestStackList_Push(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want interface{}
	}{
		{"EmptyStackList", getEmptyStackList(), 4},
		{"OneElementLongStackList", getOneElementLongStackList(), 4},
		{"ManyElementsLongStackList", getManyElementLongStackList(), 4},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			v.data.Push(v.want)
			got := v.data.elements.GetFirstElement()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestStackList_Pop(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want interface{}
	}{
		{"EmptyStackList", getEmptyStackList(), nil},
		{"OneElementLongStackList", getOneElementLongStackList(), 0},
		{"ManyElementsLongStackList", getManyElementLongStackList(), 9},
	}

	for _, v := range dataTable {
		t.Run(v.name, func(t *testing.T) {
			got := v.data.Pop()
			if got != v.want {
				t.Errorf("It expected %v, but got %v", v.want, got)
			}
		})
	}
}

func TestStackList_Top(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want interface{}
	}{
		{"EmptyStackList", getEmptyStackList(), nil},
		{"OneElementLongStackList", getOneElementLongStackList(), 0},
		{"ManyElementsLongStackList", getManyElementLongStackList(), 9},
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

func TestStackList_Size(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want uint16
	}{
		{"EmptyStackList", getEmptyStackList(), uint16(0)},
		{"OneElementLongStackList", getOneElementLongStackList(), uint16(1)},
		{"ManyElementsLongStackList", getManyElementLongStackList(), uint16(10)},
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

func TestStackList_IsEmpty(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want bool
	}{
		{"EmptyStackList", getEmptyStackList(), true},
		{"OneElementLongStackList", getOneElementLongStackList(), false},
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

func TestStackList_IsFull(t *testing.T) {
	dataTable := []struct {
		name string
		data StackList
		want bool
	}{
		{"EmptyStackList", getEmptyStackList(), false},
		{"OneElementLongStackList", getOneElementLongStackList(), false},
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
