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
    public int solution(int n, int[][] vertex) {
        int answer = 0;

        // 그래프 초기화
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        // 거리 배열 초기화
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int[] child : vertex){
            int v1 = child[0];
            int v2 = child[1];
            graph.get(v1).add(new Node(v2, 1));
            graph.get(v2).add(new Node(v1, 1));
        }
        
        dijkstra(1);
        
        int max = 0;
        for(int i = 1; i <= n; i++){
            if(dist[i] > max) max = dist[i];
        }
        
        for(int i = 1; i<= n; i++){
            if(dist[i] == max) answer++;
        }

        return answer;
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