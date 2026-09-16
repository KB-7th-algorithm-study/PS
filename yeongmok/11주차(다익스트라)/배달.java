import java.util.*;

class Node {
	int idx;
	int cost;

	// 생성자
	Node(int idx, int cost) {
		this.idx = idx;
		this.cost = cost;
	}
}


class Solution {
    public int solution(int N, int[][] road, int K) {
        int start = 1;
        // 1. 인접리스트를 이용한 그래프 초기화
		List<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        
        // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기만 한다.
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
        
        // 그래프에 값을 넣는다.
		for (int i = 0; i < road.length; i++) {
			// a로 부터 b로 가는 값은 cost이다.
			int a = road[i][0];
			int b = road[i][1];
			int cost = road[i][2];

			graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
		}
        
        // 2. 방문 여부를 확인할 boolean 배열, start 노드부터 end 노드 까지의 최소 거리를 저장할 배열을 만든다.
		boolean[] visited = new boolean[N + 1];
		int[] dist = new int[N + 1];
        // 3. 최소 거리 정보를 담을 배열을 초기화 한다.
		for (int i = 0; i < N + 1; i++) {
			// 출발 지점 외 나머지 지점까지의 최소 비용은 최대로 지정해둔다.
			dist[i] = Integer.MAX_VALUE;
		}
        // 출발 지점의 비용은 0으로 시작한다.
		dist[start] = 0;
        
        // 4. 다익스트라 알고리즘을 진행한다.
		// 모든 노드을 방문하면 종료하기 때문에, 노드의 개수 만큼만 반복을 한다.
		for (int i = 0; i < N; i++) {
			// 4 - 1. 현재 거리 비용 중 최소인 지점을 선택한다.
			// 해당 노드가 가지고 있는 현재 비용.
			int nodeValue = Integer.MAX_VALUE;
			// 해당 노드의 인덱스(번호).
			int nodeIdx = 0;
			// 인덱스 0은 생각하지 않기 때문에 0부터 반복을 진행한다.
			for (int j = 1; j < N + 1; j++) {
				// 해당 노드를 방문하지 않았고, 현재 모든 거리비용 중 최솟값을 찾는다.
				if (!visited[j] && dist[j] < nodeValue) {
					nodeValue = dist[j];
					nodeIdx = j;
				}
			}
			// 최종 선택된 노드를 방문처리 한다.
			visited[nodeIdx] = true;

			// 4 - 2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
			for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
				// 인접 노드를 선택한다.
				Node adjNode = graph.get(nodeIdx).get(j);
				// 인접 노드가 현재 가지는 최소 비용과
				// 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
				if (dist[adjNode.idx] > dist[nodeIdx] + adjNode.cost) {
					dist[adjNode.idx] = dist[nodeIdx] + adjNode.cost;
				}
			}
		}
        
        // 5. 최종 비용을 출력한다.
        int answer = 0;
		for (int i = 1; i < N + 1; i++) {
			if (dist[i] != Integer.MAX_VALUE && dist[i]<=K) {
				answer+=1;
			} 
		}
        
        return answer;
    }
}