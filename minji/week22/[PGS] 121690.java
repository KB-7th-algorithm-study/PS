import java.util.*;

// [PGS] 121690 [PCCP 모의고사 2] 4번 - 보물 지도
class Solution {
    public int solution(int n, int m, int[][] hole) {
        boolean[][] trap = new boolean[n + 1][m + 1];
        for (int[] h : hole) trap[h[0]][h[1]] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        // dist[x][y][used]: used = 신발 사용 여부(0: 미사용, 1: 사용)
        int[][][] dist = new int[n + 1][m + 1][2];
        for (int[][] plane : dist) {
            for (int[] row : plane) Arrays.fill(row, -1);
        }

        // BFS: 걷기와 점프 모두 시간 1
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 1, 0});
        dist[1][1][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], used = cur[2];
            if (x == n && y == m) return dist[x][y][used];

            for (int d = 0; d < 4; d++) {
                // 걸어서 한 칸 이동
                int nx = x + dx[d], ny = y + dy[d];
                if (canMove(n, m, trap, nx, ny) && dist[nx][ny][used] == -1) {
                    dist[nx][ny][used] = dist[x][y][used] + 1;
                    q.offer(new int[]{nx, ny, used});
                }

                // 신발로 두 칸 점프 (중간 칸의 함정은 넘을 수 있음)
                if (used == 1) continue;
                int jx = x + dx[d] * 2, jy = y + dy[d] * 2;
                if (canMove(n, m, trap, jx, jy) && dist[jx][jy][1] == -1) {
                    dist[jx][jy][1] = dist[x][y][0] + 1;
                    q.offer(new int[]{jx, jy, 1});
                }
            }
        }

        return -1;
    }

    private boolean canMove(int n, int m, boolean[][] trap, int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= m && !trap[x][y];
    }
}
