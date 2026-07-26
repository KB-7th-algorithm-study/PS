import java.util.*;

class Solution {
    int[] dy = {-1,0,1,0};
    int[] dx = {0,1,0,-1};
    boolean[][] visited = new boolean[104][104];
    int sum;
    void dfs(int y, int x, String[] maps){
        visited[y][x] = true;
        sum += maps[y].charAt(x) - '0';
        for(int i =0; i<4; i++){
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny <0 || nx < 0 || ny >= maps.length || nx >=maps[0].length()) continue;
            if(maps[ny].charAt(nx) == 'X') continue;
            if(visited[ny][nx]) continue;
            dfs(ny,nx,maps);
        }
    }
    
    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        List<Integer> answer = new ArrayList<>();
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X') {
                    sum=0;
                    dfs(i,j,maps);
                    answer.add(sum);
                }
            }
        }
        if(answer.isEmpty()) return new int[]{-1};
        Collections.sort(answer);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}