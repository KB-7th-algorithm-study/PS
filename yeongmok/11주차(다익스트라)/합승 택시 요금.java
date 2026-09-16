import java.util.*;

class Node{
    int idx;
    int value;
    
    Node(int i, int v){
        this.idx = i;
        this.value = v;
    }
}

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<ArrayList<Node>> graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<fares.length;i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int val = fares[i][2];
            
            graph.get(start).add(new Node(end,val));
            graph.get(end).add(new Node(start,val));
        }
        
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        
        for(int i=0;i<dist.length;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        
        for(int i=0;i<=n;i++){
            int nodeIdx = 0;
            int nodeValue = Integer.MAX_VALUE;
            
            for(int j=1;j<=n;j++){
                if(!visited[j] && nodeValue>dist[j]){
                    nodeIdx = j;
                    nodeValue = dist[j];
                }
            }
            
			visited[nodeIdx] = true;

            // 4 - 2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
			for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
				// 인접 노드를 선택한다.
				Node adjNode = graph.get(nodeIdx).get(j);
				// 인접 노드가 현재 가지는 최소 비용과
				// 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
				if (dist[adjNode.idx] > dist[nodeIdx] + adjNode.value) {
					dist[adjNode.idx] = dist[nodeIdx] + adjNode.value;
				}
			} 
        }
        
        //!
        int min = Integer.MAX_VALUE;
        int q = 0;
        for(int k=1;k<=n;k++){
            visited = new boolean[n+1];
            int[] dist2 = new int[n+1];

            for(int i=0;i<dist2.length;i++){
                dist2[i] = Integer.MAX_VALUE;
            }
            dist2[k] = 0;

            for(int i=0;i<=n;i++){
                int nodeIdx = 0;
                int nodeValue = Integer.MAX_VALUE;

                for(int j=1;j<=n;j++){
                    if(!visited[j] && nodeValue>dist2[j]){
                        nodeIdx = j;
                        nodeValue = dist2[j];
                    }
                }

                visited[nodeIdx] = true;

                // 4 - 2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
                for (int j = 0; j < graph.get(nodeIdx).size(); j++) {
                    // 인접 노드를 선택한다.
                    Node adjNode = graph.get(nodeIdx).get(j);
                    // 인접 노드가 현재 가지는 최소 비용과
                    // 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
                    if (dist2[adjNode.idx] > dist2[nodeIdx] + adjNode.value) {
                        dist2[adjNode.idx] = dist2[nodeIdx] + adjNode.value;
                    }
                } 
            }
min = dist[k]+dist2[a]+dist2[b] > min ? min : dist[k]+dist2[a]+dist2[b];
                if(min==dist2[a]+dist2[b]+dist[k]){
                    q=k;
                }
        }
        
        return min;
    }
}