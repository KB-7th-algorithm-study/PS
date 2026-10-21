import java.util.*;

//최적화
class Solution {

  static int[] dy = {1, -1, 0, 0};
  static int[] dx = {0, 0, 1, -1};

  public int solution(String[] board) {

    int n = board.length;
    int m = board[0].length();

    int[][] visited = new int[n][m];

    for (int i = 0; i < n; i++) {
      Arrays.fill(visited[i], -1);
    }

    int sy = 0;
    int sx = 0;

    // 시작점 찾기
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i].charAt(j) == 'R') {
          sy = i;
          sx = j;
        }
      }
    }

    Deque<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{sy, sx});
    visited[sy][sx] = 0;

    while (!queue.isEmpty()) {

      int[] cur = queue.poll();
      int y = cur[0];
      int x = cur[1];

      if (board[y].charAt(x) == 'G') {
        return visited[y][x];
      }

      // 상하좌우로 끝까지 이동
      for (int i = 0; i < 4; i++) {

        int ny = y;
        int nx = x;

        while (true) {
          int nextY = ny + dy[i];
          int nextX = nx + dx[i];

          if (nextY < 0 || nextY >= n ||
              nextX < 0 || nextX >= m ||
              board[nextY].charAt(nextX) == 'D') {
            break;
          }

          ny = nextY;
          nx = nextX;
        }

        if (visited[ny][nx] != -1) continue;

        visited[ny][nx] = visited[y][x] + 1;
        queue.offer(new int[]{ny, nx});
      }
    }

    return -1;
  }
}

//첫 정답
//import java.util.*;
//
//class Solution {
//
//  static int[] dx = {0,0,-1,1};
//  static int[] dy = {1,-1,0,0};
//
//  public int findG(String[] board, int[] start, int[][] visited){
//
//    Deque<int[]> moveMap = new ArrayDeque<>();
//    moveMap.add(start);
//
//    int by = board.length;
//    int bx = board[0].length();
//
//    while(!moveMap.isEmpty()){
//
//      int[] cur = moveMap.poll();
//      int count = visited[cur[0]][cur[1]];
//
//      if (board[cur[0]].charAt(cur[1]) == 'G') return count;
//
//
//      //상하좌우 이동
//      for (int i = 0; i < 4; i++){
//
//        int ny = cur[0];
//        int nx = cur[1];
//
//        while(isRightPos(board, by, bx, ny + dy[i], nx + dx[i])){
//          ny += dy[i];
//          nx += dx[i];
//        }
//
//        //방문 안함
//        if (visited[ny][nx] == -1){
//          visited[ny][nx] = count + 1;
//          moveMap.add(new int[] {ny, nx});
//        }
//      }
//    }
//
//    return -1;
//  }
//
//  public boolean isRightPos(
//      String[] board, int boardY,
//      int boardX, int y, int x){
//    return (y >= 0 && y < boardY) && (x >= 0 && x < boardX)
//        && board[y].charAt(x) != 'D';
//  }
//
//  public int solution(String[] board) {
//
//    int[][] visited = new int[board.length][board[0].length()];
//
//    for (int i = 0; i < board.length; i++){
//      Arrays.fill(visited[i], -1);
//    }
//
//    int[] start = {0};
//
//    //R좌표 찾기
//    for (int i = 0; i < board.length; i++){
//      for (int j = 0; j < board[0].length(); j++){
//        if (board[i].charAt(j) == 'R'){
//          start = new int[] {i, j};
//          visited[i][j] = 0;
//        }
//      }
//    }
//
//    return findG(board, start, visited);
//
//  }
//}
