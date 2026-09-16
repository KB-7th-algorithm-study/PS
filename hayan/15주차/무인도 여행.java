import java.util.*;

class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int[][] map;
    boolean[][] visited;
    int sum;
    int n;
    int m;
    public int[] solution(String[] maps) {
        List<Integer> ans = new ArrayList<>();
        
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char c = maps[i].charAt(j);
                map[i][j] = (c=='X') ? 0 : c-'0';
            }
        }
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(visited[i][j]==false && map[i][j]!=0){
                    sum = 0;
                    dfs(i, j);
                    ans.add(sum);
                }
            }
        }
        
        if(ans.isEmpty()){
            return new int[]{-1};
        }
        
        Collections.sort(ans);
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    public void dfs(int x, int y){
        visited[x][y] = true;
        sum += map[x][y];
        
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            if(visited[nx][ny]==false && map[nx][ny]!=0){
                dfs(nx, ny);
            }
        }
    }
}