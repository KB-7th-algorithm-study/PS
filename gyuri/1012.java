import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, M, N, K;
    static int[][] map, visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수

        // 1. 정보 입력
        for(int i = 0; i < T; i++) {
            int cnt = 0;
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); // 가로 길이
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            K = Integer.parseInt(st.nextToken()); // 위치의 개수

            map = new int[N][M];
            visited = new int[N][M];

            // 2. 배추 위치 = 1
            for(int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());

                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());

                map[n][m] = 1;
            }

            // 3. dfs
            for(int a = 0; a < N; a++) {
                for(int b = 0; b < M; b++) {
                    if(visited[a][b] == 0 && map[a][b] == 1) {
                        dfs(a, b);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);
        }
    }

    public static void dfs(int x, int y) {
        visited[x][y] = 1;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내 좌표인지 확인
            if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                // 배추가 있고 아직 방문하지 않은 경우
                if(visited[nx][ny] == 0 && map[nx][ny] == 1) {
                    dfs(nx, ny);
                }
            }
        }
    }
}