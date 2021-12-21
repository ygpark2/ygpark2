package dequeArray

import (
	"ds/internal/deque"
	"errors"
)

type DequeArray struct {
	elements []interface{}
	front    int16
	back     int16
	size     uint16
	capacity uint16
}

// 데큐 생성
func NewDequeArray(capacity uint16) deque.Deque {
	return &DequeArray{
		elements: make([]interface{}, int(capacity)), // 배열 생성
		front:    int16(-1),                          // front 초기화
		back:     int16(-1),                          // back 초기화
		size:     uint16(0),                          // size 초기화
		capacity: capacity,                           // capacity 초기화
	}
}

// 앞부분에 추가
func (da *DequeArray) AddFront(data interface{}) error {
	if da.IsFull() { // 데큐가 꽉 찼는지 확인
		return errors.New("Deque is full") // 꽉 찼으면 에러 반환
	}

	if da.front == -1 && da.back == -1 { // 데큐가 비어있는지 확인
		da.front++ //	앞부분에 추가
		da.back++  //	뒷부분에 추가
	} else {
		if da.front == 0 {
			da.front = int16(da.capacity) - 1
		} else {
			da.front--
		}
	}
	da.elements[da.front] = data
	da.size++
	return nil
}

func (da *DequeArray) AddBack(data interface{}) error {
	if da.IsFull() {
		return errors.New("Deque is full")
	}

	if da.front == -1 && da.back == -1 {
		da.front++
		da.back++
	} else {
		if da.back == int16(da.capacity)-1 {
			da.back = int16(0)
		} else {
			da.back++
		}
	}

	da.elements[da.front] = data
	da.size++
	return nil
}

func (da *DequeArray) RemoveFront() interface{} {
	if da.IsEmpty() {
		return nil
	}

	var data interface{}
	if da.front == da.back {
		data = da.elements[da.front]
		da.front = -1
		da.back = -1
	} else {
		if da.front == int16(da.capacity)-1 {
			data = da.elements[da.front]
			da.front = 0
		} else {
			data = da.elements[da.front]
			da.front++
		}
	}
	da.size--
	return data
}

func (da *DequeArray) RemoveBack() interface{} {
	if da.IsEmpty() {
		return nil
	}

	var data interface{}
	if da.front == da.back {
		data = da.elements[da.back]
		da.front = -1
		da.back = -1
	} else {
		if da.back == 0 {
			data = da.elements[da.back]
			da.back = int16(da.capacity) - 1
		} else {
			data = da.elements[da.back]
			da.back--
		}
	}
	da.size--
	return data
}

func (da *DequeArray) PeekFirst() interface{} {
	if da.IsEmpty() {
		return nil
	}

	return da.elements[da.front]
}

func (da *DequeArray) PeekLast() interface{} {
	if da.IsEmpty() {
		return nil
	}

	return da.elements[da.back]
}

func (da *DequeArray) IsFull() bool {
	return da.back+1 == da.front || (da.front == 0 && da.back == int16(da.capacity)-1)
}

func (da *DequeArray) IsEmpty() bool {
	return da.front == -1 && da.back == -1
}

func (da *DequeArray) Size() uint16 {
	return da.size
}
