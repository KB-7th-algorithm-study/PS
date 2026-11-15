// https://school.programmers.co.kr/learn/courses/15009/lessons/121690
// [프로그래머스] [PCCP 모의고사 2] 4번 - 보물 지도 : Java
import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] hole) {
        // 최소 시간 구하기 -> BFS
        // 신발 사용 여부 트래킹하기
        // 문제에서 주어진대로 활용하기 -> n+1, m+1 크기의 2차원 배열
        
        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, 1, 0, -1};
        
        int[][] board = new int[n + 1][m + 1]; // (c, r)
        boolean[][][] visited = new boolean[n + 1][m + 1][2];
        
        for (int[] trap: hole) {
            board[trap[0]][trap[1]] = -1;
        }
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 1, 0, 0}); // (x, y, isShoeUsed, dist)
        visited[1][1][0] = true;
        
        while (!q.isEmpty()) {
            int[] curPos = q.poll();
            
            int curX = curPos[0];
            int curY = curPos[1];
            int isShoeUsed = curPos[2];
            int dist = curPos[3];
            
            if (curX == n && curY == m) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= m 
                    && !visited[nx][ny][isShoeUsed] && board[nx][ny] != -1) {
                    q.offer(new int[] {nx, ny, isShoeUsed, dist + 1});
                    visited[nx][ny][isShoeUsed] = true;
                }
                
                if (isShoeUsed == 0) { // 신발을 사용하지 않은 경우 점프 고려
                    nx = curX + 2 * dx[i];
                    ny = curY + 2 * dy[i];

                    if (nx >= 1 && nx <= n && ny >= 1 && ny <= m 
                        && !visited[nx][ny][1] && board[nx][ny] != -1) {
                        q.offer(new int[] {nx, ny, 1, dist + 1});
                        visited[nx][ny][1] = true;
                    }
                }
            }            
        }
        
        return -1;
    }
}