import java.util.*;

public class Main {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    static int[][] map;
    static int M,N,sNum;
    static boolean[][] visited;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for(int i=0;i<n;i++) {
            M = sc.nextInt();
            N = sc.nextInt();
            int num = sc.nextInt();

            visited = new boolean[M][N];
            map = new int[M][N];

            for (int j = 0; j < num; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();

                map[x][y]++;
            }

            //위에까지 입력값 처리, 아래부터 필요한 벌레 수 계산
            sNum = 1;
            for(int j=0;j<M;j++){
                for(int k=0;k<N;k++){
                    if(map[j][k]!=0 && visited[j][k]!=true){
                        BFS(j,k);
                        sNum++;
                    }
                }
            }
            System.out.println(sNum-1);
        }


    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {i,j};

        queue.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                //map 범위를 넘지 않게 범위 설정
                if(nr >= 0 && nr < M && nc >= 0 && nc < N){
                    //방문 안한 곳 && 배추가 심어진 곳 확인
                    if(!visited[nr][nc] && map[nr][nc] != 0){
                        addNode(nr, nc, queue);
                    }
                }
            }
        }
    }

    private static void addNode(int i, int j, Queue<int[]> queue){
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = {i,j};
        queue.add(temp);
    }
}