import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] visited;
    static int N, P, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터의 수
        map = new int[N+1][N+1];
        visited = new int[N+1];

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken()); // 컴퓨터 쌍의 수

        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
            map[y][x] = 1;
        }

        bfs();

        System.out.println(cnt);
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();

        // 처음 감염된 바이러스
        q.add(1);
        visited[1] = 1;

        while(!q.isEmpty()) {
            int node = q.poll();

            // 컴퓨터 수만큼 돌면서 연결된 노드를 큐에 추가
            for(int i = 1; i <= N; i++) {
                if(map[node][i] == 1 && visited[i] == 0) {
                    q.add(i);
                    visited[i] = 1;
                    cnt++;
                }
            }
        }
    }
}