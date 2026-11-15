import java.util.*;

// [PGS] 169199 리코쳇 로봇
class Solution {
    public int solution(String[] board) {
        int n = board.length, m = board[0].length();
        int sr = 0, sc = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') { sr = i; sc = j; }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);

        // BFS: 한 번 미끄러지는 것을 1회 이동으로 계산
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        dist[sr][sc] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            if (board[r].charAt(c) == 'G') return dist[r][c];

            for (int d = 0; d < 4; d++) {
                int nr = r, nc = c;
                // 벽이나 장애물(D)을 만날 때까지 미끄러짐
                while (true) {
                    int tr = nr + dr[d], tc = nc + dc[d];
                    if (tr < 0 || tr >= n || tc < 0 || tc >= m) break;
                    if (board[tr].charAt(tc) == 'D') break;
                    nr = tr; nc = tc;
                }
                if (dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        return -1;
    }
}
