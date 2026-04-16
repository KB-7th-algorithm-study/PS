package PS.minji;

import java.io.*;
import java.util.*;

public class maze_2178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(세로), M(가로) 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 미로 생성
        int[][] maze = new int[N][M];

        // 미로 채우기
        for(int i=0; i<N; i++) {
            // 미로 한 줄 입력받기
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                maze[i][j] = (input.charAt(j) - '0');
            }
        }

        // Queue 준비
        Queue<int[]> q = new LinkedList<>();
        // 시작점(0,0) 넣기
        q.add(new int[]{0,0});
        // 방문 처리 배열 준비
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        // q 안에 좌표가 있다면
        while(!q.isEmpty()) {
            // 현재 좌표 넣기
            int[] current = q.poll();
            int y = current[0];
            int x = current[1];

            for(int i=0; i<4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 미로 범위 안에 있고
                if(ny>=0 && ny<N && nx>=0 && nx<M) {
                    // 길이 있으며(1) 아직 방문하지 않았다면
                    if(maze[ny][nx] == 1 && !visited[ny][nx]) {
                        // 그 위치에 방문처리
                        visited[ny][nx] = true;
                        maze[ny][nx] = maze[y][x] + 1;

                        q.add(new int[]{ny, nx});
                    }
                }
            }
        }
        System.out.println(maze[N-1][M-1]);
    }
}
