
import java.util.*;

class Solution {

  static int[] dx = {1, -1, 0, 0};
  static int[] dy = {0, 0, 1, -1};

  public int solution(int n, int m, int[][] hole) {

    boolean[][] map = new boolean[n + 1][m + 1];
    boolean[][][] visited = new boolean[n + 1][m + 1][2];

    // 함정 위치 표시
    for (int[] h : hole) {
      map[h[0]][h[1]] = true;
    }

    Deque<int[]> queue = new ArrayDeque<>();
    queue.add(new int[]{1, 1, 0, 0});
    visited[1][1][0] = true;

    while (!queue.isEmpty()) {

      int[] cur = queue.poll();
      int x = cur[0];
      int y = cur[1];
      int used = cur[2];
      int count = cur[3];

      if (x == n && y == m) return count;

      for (int i = 0; i < 4; i++) {

        // 일반 이동, 신발 사용 시 2칸 이동
        for (int move = 1; move <= 2; move++) {

          if (move == 2 && used == 1) continue;

          int nx = x + dx[i] * move;
          int ny = y + dy[i] * move;
          int nextUsed = (move == 2) ? 1 : used;

          if (nx < 1 || ny < 1 || nx > n || ny > m) continue;
          if (map[nx][ny]) continue;
          if (visited[nx][ny][nextUsed]) continue;

          visited[nx][ny][nextUsed] = true;
          queue.add(new int[]{nx, ny, nextUsed, count + 1});
        }
      }
    }

    return -1;
  }


//
//    public int solution(int n, int m, int[][] hole) {
//
//        int[][] map = new int[n + 1][m + 1];
//
//        for (int i = 0; i < hole.length; i++) {
//            map[hole[i][0]][hole[i][1]] = 1;
//        }
//
//        boolean[][][] visited = new boolean[n + 1][m + 1][2];
//        Deque<int[]> q = new ArrayDeque<>();
//
//        int[] dx = {0, 0, 1, -1};
//        int[] dy = {1, -1, 0, 0};
//
//        q.add(new int[]{1, 1, 0, 0});
//        visited[1][1][0] = true;
//
//        while (!q.isEmpty()) {
//
//            int[] now = q.poll();
//
//            int x = now[0];
//            int y = now[1];
//            int shoe = now[2];
//            int cnt = now[3];
//
//            if (x == n && y == m) {
//                return cnt;
//            }
//
//            // 한 칸씩 이동
//            for (int i = 0; i < 4; i++) {
//                int nx = x + dx[i];
//                int ny = y + dy[i];
//
//                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
//                if (map[nx][ny] == 1) continue;
//
//                if (!visited[nx][ny][shoe]) {
//                    visited[nx][ny][shoe] = true;
//                    q.add(new int[]{nx, ny, shoe, cnt + 1});
//                }
//            }
//
//            // 신발을 아직 안 쓴 경우 2칸 점프
//            if (shoe == 0) {
//
//                for (int i = 0; i < 4; i++) {
//                    int nx = x + dx[i] * 2;
//                    int ny = y + dy[i] * 2;
//
//                    if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
//                    if (map[nx][ny] == 1) continue;
//
//                    if (!visited[nx][ny][1]) {
//                        visited[nx][ny][1] = true;
//                        q.add(new int[]{nx, ny, 1, cnt + 1});
//                    }
//                }
//            }
//        }
//
//        return -1;
//    }
}
