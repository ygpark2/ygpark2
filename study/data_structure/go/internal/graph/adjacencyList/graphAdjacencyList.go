package adjacencyList

import (
	"ds/internal/graph"
	"ds/internal/list"
	"ds/internal/list/doublylinkedlist"
	"ds/internal/queue/queuelist"
	"ds/internal/stack"
	"ds/internal/stack/stacklist"
	"fmt"
)

type GraphAdjacencyList struct {
	elements []list.List
	size     uint16 // number of edges
	order    uint16 // number of vertices
}

func NewGraphAdjacencyList(order uint16) graph.Graph {
	elements := make([]list.List, order)
	for i := range elements {
		elements[i] = doublylinkedlist.NewDoublyLinkedListEmp()
	}
	return &GraphAdjacencyList{
		elements: elements,
		size:     0,
		order:    order,
	}
}

func (g GraphAdjacencyList) Elements() []list.List {
	return g.elements
}

// 방향성이 없이 때문에 양방향 연결
func (g GraphAdjacencyList) AddEdge(node1 uint16, node2 uint16) error {
	g.elements[node1].AddAtEnd(node2)
	g.elements[node2].AddAtEnd(node1)
	g.size++
	return nil
}

// 방향성이 있기 때문에 한 쪽 방향만 추가
func (g GraphAdjacencyList) AddEdgeDirected(node1 uint16, node2 uint16) error {
	g.elements[node1].AddAtEnd(node2)
	g.size++
	return nil
}

// 깊이 우선 탐색
// BreathFirstTraverse assumes the first element is the root
func (g GraphAdjacencyList) BreathFirstTraverse(root uint16) {
	visited := make([]bool, cap(g.elements)) // 방문 여부 표시
	queue := queuelist.NewLinearQueueEmp()   // 순차 큐 정의
	visited[root] = true                     // 루트 노드를 방문 처리
	queue.Enqueue(root)                      // 루트 노드를 큐에 삽입
	for !queue.IsEmpty() {                   // 큐가 비어있지 않을 동안
		data := queue.Dequeue()                                       // 큐에서 노드를 꺼냄
		fmt.Printf("%v -> ", data)                                    // 노드를 출력
		for i := 1; i <= int(g.elements[data.(uint16)].Size()); i++ { // 각 노드에 연결된 노드들을 탐색
			value := g.elements[data.(uint16)].Get(uint16(i)).(uint16) // 각 노드에 연결된 노드를 꺼냄
			if !visited[value] {                                       // 방문 여부 확인
				queue.Enqueue(value)  // 노드를 큐에 삽입
				visited[value] = true // 방문 처리
			}
		}
	}
}

// 너비 우선 탐색
func (g GraphAdjacencyList) DepthFirstSearch(root uint16) {
	visited := make([]bool, g.order)     // 방문 여부 표시
	stack := stacklist.NewStackListEmp() // 스택 정의
	stack.Push(root)                     // 루트 노드를 스택에 삽입
	var val uint16                       // 탐색 중인 노드
	for !stack.IsEmpty() {               // 스택이 비어있지 않을 동안
		val = stack.Pop().(uint16) // 스택에서 노드를 꺼냄 처음은 루트 노드를 꺼냄
		if !visited[val] {         // 방문 여부 확인
			visited[val] = true                                 // 방문 처리
			fmt.Printf("%v ->", val)                            // 노드를 출력
			for i := 1; i <= int(g.elements[val].Size()); i++ { // 각 노드에 연결된 노드들을 탐색
				pos := g.elements[val].Get(uint16(i)).(uint16) // 각 노드에 연결된 노드를 꺼냄
				if !visited[pos] {                             // 방문 여부 확인
					stack.Push(pos) // 노드를 스택에 삽입
				}
			}
		}
	}
}

func (g GraphAdjacencyList) DepthFirstSearchRecursive() {
	visited := make([]bool, g.order)
	for i := 0; i < int(g.order); i++ {
		if !visited[i] {
			g.DepthFirstSearchRecursiveCall(i, visited)
		}
	}
}

func (g GraphAdjacencyList) DepthFirstSearchRecursiveCall(vertex int, visited []bool) {
	visited[vertex] = true
	fmt.Printf("%v ->", vertex)
	for i := 1; i <= int(g.elements[vertex].Size()); i++ {
		val := g.elements[vertex].Get(uint16(i)).(uint16)
		if !visited[int(val)] {
			g.DepthFirstSearchRecursiveCall(int(val), visited)
		}
	}
}

// 무방향성 그래프에서 사이클이 있는지 확인
func (g GraphAdjacencyList) CycleDetectionUndirected() bool {
	visited := make([]bool, g.order)    // 방문 여부 표시
	for i := 0; i < int(g.order); i++ { // 모든 노드를 탐색
		if !visited[i] { // 방문 여부 확인
			if g.cycleDetectionUndirected(i, visited, -1) { // 사이클이 있는지 확인
				return true // 사이클이 있으면 true 반환
			}
		}
	}
	return false // 사이클이 없으면 false 반환
}

func (g GraphAdjacencyList) cycleDetectionUndirected(vertex int, visited []bool, parent int) bool {
	visited[vertex] = true
	for i := 1; i <= int(g.elements[vertex].Size()); i++ {
		val := g.elements[vertex].Get(uint16(i)).(uint16)
		if !visited[val] {
			if g.cycleDetectionUndirected(int(val), visited, vertex) {
				return true
			}
		} else if int(val) != parent {
			return true
		}
	}
	return false
}

// 방향 그래프에서 사이클이 있는지 확인
func (g GraphAdjacencyList) CycleDetectionDirected() bool {
	visited := make([]bool, g.order)    // 방문 여부 표시
	curStack := make([]bool, g.order)   // 현재 스택
	for i := 0; i < int(g.order); i++ { // 모든 노드를 탐색
		if g.cycleDetectionDirected(i, visited, curStack) { // 사이클이 있는지 확인
			return true // 사이클이 있으면 true 반환
		}
	}
	return false // 사이클이 없으면 false 반환
}

func (g GraphAdjacencyList) cycleDetectionDirected(vertex int, visited []bool, curStack []bool) bool {
	if curStack[vertex] { // 현재 스택에 이미 있는 노드라면
		return true // 사이클이 있는지 확인
	}

	if visited[vertex] { // 방문 여부 확인
		return false // 방문한 노드라면 false 반환
	}

	curStack[vertex] = true // 현재 스택에 삽입
	visited[vertex] = true  // 방문 처리

	for i := 1; i <= int(g.elements[vertex].Size()); i++ { // 각 노드에 연결된 노드들을 탐색
		val := g.elements[vertex].Get(uint16(i)).(uint16)          // 각 노드에 연결된 노드를 꺼냄
		if g.cycleDetectionDirected(int(val), visited, curStack) { // 각 노드에 연결된 노드들을 탐색
			return true // 사이클이 있으면 true 반환
		}
	}
	curStack[vertex] = false // 현재 스택에서 제거
	return false             // 사이클이 없으면 false 반환
}

func (g GraphAdjacencyList) TopologicalSort() []int {
	visited := make([]bool, g.order)
	stack := stacklist.NewStackListEmp()

	for i := 0; i < int(g.order); i++ {
		if !visited[i] {
			g.topologicalSort(i, visited, stack)
		}
	}

	order := make([]int, stack.Size())
	for i := 0; i < len(order); i++ {
		order[i] = stack.Pop().(int)
	}

	return order
}

func (g GraphAdjacencyList) topologicalSort(vertex int, visited []bool, stack stack.Stack) {
	visited[vertex] = true

	for i := 1; i <= int(g.elements[vertex].Size()); i++ {
		val := g.elements[vertex].Get(uint16(i)).(uint16)
		if !visited[val] {
			g.topologicalSort(int(val), visited, stack)
		}
	}

	stack.Push(vertex)
}

func (g GraphAdjacencyList) Transpose() graph.Graph {
	transposed := NewGraphAdjacencyList(g.order)
	for i := 0; i < int(g.order); i++ {
		for j := 1; j <= int(g.elements[i].Size()); j++ {
			val := g.elements[i].Get(uint16(j)).(uint16)
			transposed.AddEdgeDirected(val, uint16(i))
		}
	}
	return transposed
}

func (g GraphAdjacencyList) StronglyConnectedComponents() {
	visited := make([]bool, g.order)
	stack := stacklist.NewStackListEmp()
	for i := 0; i < int(g.order); i++ {
		if !visited[i] {
			g.topologicalSort(i, visited, stack)
		}
	}

	transposed := g.Transpose()

	for i := 0; i < int(g.order); i++ {
		visited[i] = false
	}

	for !stack.IsEmpty() {
		vertex := stack.Pop().(int)
		if !visited[vertex] {
			transposed.DepthFirstSearchRecursiveCall(vertex, visited)
			fmt.Println()
		}
	}
}

func (g GraphAdjacencyList) MotherGraph() int {
	visited := make([]bool, g.order)
	var lastVisited int
	for i := 0; i < int(g.order); i++ {
		if !visited[i] {
			g.DepthFirstSearchRecursiveCall(i, visited)
			lastVisited = i
		}
	}

	for i := 0; i < int(g.order); i++ {
		visited[i] = false
	}

	g.DepthFirstSearchRecursiveCall(lastVisited, visited)
	for i := 0; i < len(visited); i++ {
		if !visited[i] {
			return -1
		}
	}

	return lastVisited
}

func (g GraphAdjacencyList) NumberEdges(directed bool) int {
	sum := 0
	for i := 0; i < int(g.order); i++ {
		sum += int(g.elements[i].Size())
	}

	if directed {
		return sum
	}

	return sum / 2
}

func (g GraphAdjacencyList) PathExists(source int, target int) bool {
	if source == target {
		return true
	}

	visited := make([]bool, g.order)
	stack := stacklist.NewStackListEmp()

	stack.Push(source)
	visited[source] = true

	for !stack.IsEmpty() {
		vertex := stack.Pop().(int)

		for i := 1; i <= int(g.elements[vertex].Size()); i++ {
			val := g.elements[vertex].Get(uint16(i)).(uint16)
			if !visited[val] {
				if int(val) == target {
					return true
				}

				stack.Push(int(val))
				visited[vertex] = true
			}
		}
	}
	return false
}

func (g GraphAdjacencyList) HasOneParent(root int) bool {
	visited := make([]bool, g.order)

	for i := 0; i < int(g.order); i++ {
		for j := 1; j <= int(g.elements[i].Size()); j++ {
			val := g.elements[i].Get(uint16(j)).(uint16)
			if visited[val] {
				return false
			}
			visited[val] = true
		}
	}

	// assuming there's only one root
	for i := 0; i < int(g.order); i++ {
		if i == root && visited[i] {
			return false
		} else if i != root && !visited[i] {
			return false
		}
	}

	return true
}

func (g GraphAdjacencyList) ShortestPathEdges(source int, target int) int {

	visited := make([]bool, g.order)
	distance := make([]int, g.order)
	queue := queuelist.NewLinearQueueEmp()

	queue.Enqueue(source)

	for !queue.IsEmpty() {
		vertex := queue.Dequeue().(int)

		for i := 1; i <= int(g.elements[vertex].Size()); i++ {
			val := g.elements[vertex].Get(uint16(i)).(uint16)
			if !visited[val] {
				queue.Enqueue(int(val))
				visited[val] = true
				distance[val] = distance[vertex] + 1
			}

			if int(val) == target {
				return distance[val]
			}
		}
	}
	return -1
}

func (g GraphAdjacencyList) IsBipartite(root int) bool {

	coloured := make([]int, g.order)
	queue := queuelist.NewLinearQueueEmp()

	queue.Enqueue(root)
	coloured[root] = 1

	for !queue.IsEmpty() {
		parent := queue.Dequeue().(int)

		for i := 1; i <= int(g.elements[parent].Size()); i++ {
			child := g.elements[parent].Get(uint16(i)).(uint16)
			if coloured[child] == 0 {
				coloured[child] = coloured[parent] * -1
				queue.Enqueue(int(child))
			} else if coloured[child] == coloured[parent] {
				return false
			}
		}
	}
	return true

}
