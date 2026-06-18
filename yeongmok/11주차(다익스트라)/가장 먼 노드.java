import java.util.*;

class Node{
    int idx;
    
    Node(int i){
        idx = i;
    }
}
class Solution {
    public int solution(int n, int[][] edge) {
        int start = 1;
        List<ArrayList<Node>> graph = new ArrayList<>();
        
        for(int i=0;i<=n;i++){
            graph.add(new ArrayList<Node>());
        }
        
        for(int i=0;i<edge.length;i++){
            int a = edge[i][0];
            int b = edge[i][1];
            
            graph.get(a).add(new Node(b));
            graph.get(b).add(new Node(a));
        }
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        
        for(int i=0;i<dist.length;i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        
        for(int i=0;i<n;i++){
            //4-1: 젤 인접한 데 구하기
            int nodeIdx = 0;
            int nodeValue = Integer.MAX_VALUE;
            
            for(int j=1;j<n+1;j++){
                if(!visited[j] && dist[j]<nodeValue){
                    nodeIdx = j;
                    nodeValue = dist[j];
                }
            }
            visited[nodeIdx] = true;
            
            for(int j=0;j<graph.get(nodeIdx).size();j++){
                int adjDist = graph.get(nodeIdx).get(j).idx;
                if(dist[adjDist]> dist[nodeIdx]+1){
                   dist[adjDist] =  dist[nodeIdx]+1;
                }
            }
        }
        
        Arrays.sort(dist);
        
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<dist.length;i++){
            if(dist[i]!=Integer.MAX_VALUE){
                list.add(dist[i]);
            }
        }
        Collections.sort(list);
        int[] dist2 = list.stream().mapToInt(Integer::intValue).toArray();
        
        int answer = 1;
        for(int i=dist2.length-2;i>=0;i--){
            if(dist2[i]!=dist2[i+1])
                break;
            answer++;
        }
        
        return answer;
    }
}