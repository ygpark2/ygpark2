package graph

import "ds/internal/list"

type Graph interface {
	Elements() []list.List                     // 그래프의 원소들을 반환한다.
	AddEdge(uint16, uint16) error              // 두 버텍스 사이에 간선을 추가한다.
	AddEdgeDirected(uint16, uint16) error      // 두 버텍스 사이에 방향성이 있는 간선을 추가
	DepthFirstSearchRecursiveCall(int, []bool) // 재귀 호출을 사용한 깊이 우선 탐색
}
