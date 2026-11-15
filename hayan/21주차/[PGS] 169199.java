import java.util.*;

class Solution {
    public int solution(String[] board) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        
        Queue<int[]> q = new LinkedList<>();
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i].charAt(j)=='R'){
                    q.offer(new int[]{i, j, 0});
                    visited[i][j] = true;
                    break;
                }
            }
        }
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int cnt = cur[2];
            
            //지나가는 순간 아니고 멈췄을 때
            if(board[a].charAt(b)=='G'){
                return cnt;
            }
            
            for(int d=0; d<4; d++){
                int nx = a;
                int ny = b;
                
                while(true){
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];
                    
                    // 범위 벗어나거나 장애물 만나면 멈춤
                    if(tx < 0 || tx >=n || ty <0 || ty >= m || board[tx].charAt(ty)=='D'){
                        break;
                    }
                    
                    // 그게 아니면 이동
                    nx = tx;
                    ny = ty;
                }
                
                if(visited[nx][ny] == false){
                    q.offer(new int[]{nx, ny, cnt+1});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return -1;
    }
}

