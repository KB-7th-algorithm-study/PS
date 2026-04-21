package PS.minji.week2;

import java.io.*;
import java.util.StringTokenizer;

public class organic_cabbage_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 테스트 케이스 개수
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 가로
            int N = Integer.parseInt(st.nextToken()); // 세로
            int K = Integer.parseInt(st.nextToken()); // 배추 개수
            int worm = 0; // 지렁이

            // 배추밭 생성 및 0으로 초기화
            int[][] cabbageFarm = new int[N][M];
            // 지렁이 체크 (FALSE로 초기화)
            boolean[][] visited = new boolean[N][M];

            // 배추 개수만큼 반복 (배추밭 세팅)
            for(int j=0; j<K; j++) {
                // 배추 위치 입력받기
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                // 입력받은 위치에 배추 심기
                cabbageFarm[Y][X] = 1;
//                visited[Y][X] = true;
            }

            for(int y=0; y<N; y++) {
                for(int x=0;  x<M; x++) {
                    if(cabbageFarm[y][x] == 1 && !visited[y][x]) {
                        worm++;
                        // DFS 호출
                        DFS(visited, x, y, cabbageFarm);
                    }
                }
            }

            System.out.println(worm);
        }
    }

    private static void DFS(boolean[][] visited, int x, int y, int[][] cabbageFarm) {
        // 지렁이 확인한 곳 체크
        visited[y][x] = true;

        // 상하좌우 좌표 계산
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        // 새로운 좌표가 배추밭 안에 있고 배추가 있고 지렁이가 간 적이 없다면 다시 DFS 호출
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < cabbageFarm[0].length && ny >= 0 && ny < cabbageFarm.length) {
                if(cabbageFarm[ny][nx] == 1 && !visited[ny][nx]) {
                    DFS(visited, nx, ny, cabbageFarm);
                }
            }
        }
    }
}
