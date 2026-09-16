import java.util.*;

class Solution {
    int[] parent;
    public int[] findRedundantConnection(int[][] edges) {
        int[] answer = new int[2];
        int n = edges.length;

        parent = new int[n];
        
        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        for(int i=0;i<n;i++){
            //union 하기 전 부모 배열 복사
            int[] parent2 = parent.clone();

            union(edges[i][0]-1,edges[i][1]-1);
            for(int j=0;j<n;j++){
                parent[j] = find(j);
            }
            //union 한 후 부모 배열 복사
            int[] parent3= parent.clone();
            
            if(Arrays.equals(parent2, parent3)){
                answer[0] = edges[i][0];
                answer[1] = edges[i][1];
            }
            parent2=parent3.clone();
        }       
        return answer;
    }

    int find(int x){
        if(parent[x]==x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    
    void union(int i, int j){
        int rootA = find(i);
        int rootB = find(j);
        
        if(rootA!=rootB)
            parent[rootB] = rootA; 
        return;
    }
}