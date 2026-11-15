// https://school.programmers.co.kr/learn/courses/30/lessons/169199
// [프로그래머스] 리코쳇 로봇 : Java
import java.util.*;

class Solution {
    public int solution(String[] board) {
        // 도착하는 끝 지점을 visited로 판단하기
        // 움직일때는 한칸이 아니라 그 방향 끝까지 움직이기
        // 최소 이동 -> BFS
        
        int[] dr = new int[] {0, 1, 0, -1};
        int[] dc = new int[] {1, 0, -1, 0};
        int n = board.length;
        int m = board[0].length();
        boolean[][] visited = new boolean[n][m];
        int[] startPos = null;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startPos = new int[] {i, j, 0};
                }
            }
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(startPos);
        visited[startPos[0]][startPos[1]] = true;
        
        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = curPos[0];
                int nc = curPos[1];
                
                while (true) {
                    nr += dr[i];
                    nc += dc[i];
                    
                    if (nr >= n || nr < 0 || nc >= m || nc < 0) {
                        nr -= dr[i];
                        nc -= dc[i];
                        break;
                    }
                    if (board[nr].charAt(nc) == 'D') {
                        nr -= dr[i];
                        nc -= dc[i];
                        break;
                    }
                }
                
                if (board[nr].charAt(nc) == 'G') {
                    return curPos[2] + 1;
                }
                
                if (!visited[nr][nc]) {
                    q.offer(new int[] {nr, nc, curPos[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        
        return -1;
    }
}