package PS.sejong;
import java.util.*;
import java.io.*;

public class BOJ_1012 {
    static int[][] graph;
    static boolean[][] visit;
    static int M, N, worm;
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- != 0) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            graph = new int[M][N];
            visit = new boolean[M][N];
            worm = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                graph[m][n] = 1;
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] == 1 && !visit[i][j]) {
                        worm++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(worm).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (graph[nx][ny] == 1 && !visit[nx][ny]) dfs(nx, ny);
            }
        }
    }
}
