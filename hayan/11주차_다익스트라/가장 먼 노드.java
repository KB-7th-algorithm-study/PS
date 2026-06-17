import java.util.*;

class Solution {
    class Node{
        int to;
        int cost;
        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Node>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);
        
        for(int i=0; i<edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];
            
            graph[a].add(new Node(b, 1));
            graph[b].add(new Node(a, 1));
        }
        
        dist[1] = 0;
        pq.offer(new int[]{0, 1});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int d = cur[0];
            int v = cur[1];
            if(d > dist[v]) continue;
            for(Node nxt : graph[v]){
                if(dist[nxt.to] > dist[v]+nxt.cost){
                    dist[nxt.to] = dist[v]+nxt.cost;
                    pq.offer(new int[]{dist[nxt.to], nxt.to});
                }
            }
        }
        
        int max = dist[1];
        for(int i=2; i<=n; i++){
            if(dist[i]>max) max = dist[i];
        }
        for(int i=1; i<=n; i++){
            if(dist[i]==max) answer++;
        }
        
        return answer;
    }
}