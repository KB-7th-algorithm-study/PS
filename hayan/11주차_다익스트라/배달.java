import java.util.*;

class Solution {
    class Node{
        int to, cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);

        List<Node>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            
            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }
        
        dist[1] = 0;
        pq.offer(new int[]{0, 1});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int d = cur[0];
            int v = cur[1];
            if(d > dist[v]) continue;
            for(Node nxt : graph[v]){
                if(dist[nxt.to] > dist[v] + nxt.cost){
                    dist[nxt.to] = dist[v] + nxt.cost;
                    pq.offer(new int[]{dist[nxt.to], nxt.to});
                }
            }
        }
        
        for(int i=1; i<=N; i++){
            if(dist[i] <= K) answer++;
        }

        return answer;
    }
}