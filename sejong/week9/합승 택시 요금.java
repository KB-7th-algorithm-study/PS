import java.util.*;

// 노드 정의
class Node implements Comparable<Node>{
    int v;
    int w;
    
    // 생성자
    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
    
    @Override
    public int compareTo(Node o){
        return this.w - o.w;
    }
}

class Solution {
    ArrayList<ArrayList<Node>> graph;
    int[] dist;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 그래프 초기화
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        // 거리 배열 초기화
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int[] child : fares){
            int v1 = child[0];
            int v2 = child[1];
            int w = child[2];
            graph.get(v1).add(new Node(v2, w));
            graph.get(v2).add(new Node(v1, w));
        }
        
        // 합승 후 각 정점에서 떨구는 모든 경우의 수를 비교
        int min = Integer.MAX_VALUE;
        dijkstra(s);
        int[] sdist = dist.clone();
        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i = 1; i <= n; i++){
            dijkstra(i);
            int cost = sdist[i] + dist[a] + dist[b];
            min = Math.min(min, cost);
            Arrays.fill(dist, Integer.MAX_VALUE);
        }
        return min;
    }
        void dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.offer(new Node(s, 0));
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            int v = now.v;
            int w = now.w;
            
            if(w > dist[v]) continue;
            
            for(Node next : graph.get(v)){
                int nv = next.v;
                int nw = w + next.w;
                if(nw < dist[nv]){
                    dist[nv] = nw;
                    pq.offer(new Node(nv, nw));
                }
            }
            
        }
    }
}