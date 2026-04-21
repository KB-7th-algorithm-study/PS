import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, visited;
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N개의 줄
        M = Integer.parseInt(st.nextToken()); // M개의 정수

        map = new int[N][M];
        visited = new int[N][M];

        // map 정보 입력
        for(int i = 0; i < N; i++) {
            String str = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        bfs();

        System.out.println(visited[N-1][M-1]);
    }

    public static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0});
        visited[0][0] = 1;

        while(!q.isEmpty()) {
            int[] info = q.poll();
            int cx = info[0];
            int cy = info[1];

            for(int i = 0; i < 4; i++) {
                int nx = info[0] + dx[i];
                int ny = info[1] + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if(visited[nx][ny] == 0 && map[nx][ny] == 1) {
                        visited[nx][ny] = visited[cx][cy] + 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}