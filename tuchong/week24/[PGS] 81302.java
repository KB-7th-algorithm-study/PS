// https://school.programmers.co.kr/learn/courses/30/lessons/81302
// [프로그래머스] 거리두기 확인하기 : Java
import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        // 완전탐색, 모든 P 위치에서 거리두기 만족여부 확인
        int[] answer = new int[5];

        for (int p = 0; p < 5; p++) {
            char[][] board = new char[5][];
            boolean isValid = true;

            for (int i = 0; i < 5; i++) {
                board[i] = places[p][i].toCharArray();
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == 'P' && !check(i, j, board)) {
                        isValid = false;
                    }
                }
            }

            answer[p] = isValid ? 1 : 0;
        }

        return answer;
    }

    private boolean check(int row, int col, char[][] board) {
        // 파티션을 벽이라고 생각하기 -> 깊이 3 이상인 경우 stop
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        boolean[][] visited = new boolean[5][5];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {row, col, 0});
        visited[row][col] = true;

        while (!q.isEmpty()) {
            int[] curPos = q.poll();

            if (curPos[2] > 2) continue;
            if (curPos[2] != 0 && board[curPos[0]][curPos[1]] == 'P') {
                return false;
            }

            for (int i = 0; i < 4; i++) {
                int nr = curPos[0] + dr[i];
                int nc = curPos[1] + dc[i];

                if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                    continue;
                }

                if (!visited[nr][nc] && board[nr][nc] != 'X') {
                    q.offer(new int[] {nr, nc, curPos[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }

        return true;
    }
}