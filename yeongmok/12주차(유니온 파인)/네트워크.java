import java.util.*;

class Solution {
    int[] parent;
    public int solution(int n, int[][] computers) {
        parent = new int[n];

        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        for(int i=0;i<computers.length;i++){
            for(int j=0;j<n;j++){
                if(computers[i][j]==1)
                    union(i,j);
            }
        }

        for(int i=0;i<parent.length;i++){
            parent[i] = find(i);
        }
        //System.out.println(Arrays.toString(parent));
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<parent.length;i++){
            if(!list.contains(parent[i]))
                list.add(parent[i]);
        }

        return list.size();
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