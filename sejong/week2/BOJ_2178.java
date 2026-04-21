package PS.sejong.week2;
import java.util.*;
import java.io.*;

public class BOJ_2178 {
    static int N;
    static int M;
    static int[][] miro;
    static boolean[][] visit;
    static int[][] dirs = {{1,0},{-1,0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][M];
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                miro[i][j] = line.charAt(j) - '0';
            }
        }
        bfs();
        System.out.print(miro[N-1][M-1]);
    }
    static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int [] {0,0});
        while(!q.isEmpty()){
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            
            for(int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if(nx >=0 && ny >= 0 && nx < N && ny < M && miro[nx][ny] == 1 && !visit[nx][ny]){
                    miro[nx][ny] = miro[x][y] + 1;
                    visit[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }

        }
    }
}