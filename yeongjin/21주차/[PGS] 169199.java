import java.util.*;

class Solution {
    public int solution(String[] board) {
        int answer = 0;
        int rx = 0;
        int ry = 0;
        int gx = 0;
        int gy = 0;
        int height = board.length;
        int width = board[0].length();
        
        Queue<int[]> q = new LinkedList<>();
        
        for(int i =0; i< height; i++){
            for(int j =0; j<width; j++){
                if(board[i].charAt(j) == 'R'){
                    rx = j;
                    ry = i;
                }else if(board[i].charAt(j) == 'G'){
                    gx = j;
                    gy = i;
                }
            }
        }
        boolean[][] visited = new boolean[height][width];
        q.add(new int[] {ry,rx,0});
        
        visited[ry][rx] = true;
        
        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int y = curr[0];
            int x = curr[1];
            int cnt = curr[2];
            if(y == gy && x == gx){
                return cnt;
            }
            for(int i =0; i<4; i++){
                int ny = y+dy[i];
                int nx = x+dx[i];
                while(!(ny<0 || nx <0 || ny >= height || nx >= width
                       || board[ny].charAt(nx) =='D'
                       )){
                    ny = ny+dy[i];
                    nx = nx+dx[i];
                }
                if(visited[ny-dy[i]][nx-dx[i]] == true) continue;
                q.add(new int[] {ny-dy[i],nx-dx[i],cnt+1});
                visited[ny-dy[i]][nx-dx[i]] = true;
            }
        }
        return -1;
    }
}