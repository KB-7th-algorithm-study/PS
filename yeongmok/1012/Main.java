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
            sNum = 1;
            for(int j=0;j<M;j++){
                for(int k=0;k<N;k++){
                    if(map[j][k]!=0 && visited[j][k]!=true){
                        BFS(j,k);
                        sNum++;
                    }
                }
            }

            int max = 0;
            for(int j=0;j<M;j++){
                for(int k=0;k<N;k++){
                    if(map[j][k]>max) max = map[j][k];
                }
            }
            System.out.println(max);
        }


    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {i,j};

        queue.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;

        while(!queue.isEmpty()){
            //?
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            for(int d=0;d<4;d++){
                int tempR = dr[d];
                int tempC = dc[d];
                while(r+tempR>=0&& r+tempR<M && c+tempC>=0&& c+tempC<N){
                    //현재 방문한 적이 없고 바다가 아니면 같은 섬으로 취급하기
                    if(visited[r+tempR][c+tempC] == false && map[r+tempR][c+tempC] !=0){
                        addNode(r+tempR,c+tempC,queue);
                    }else break;
                    if(tempR<0)tempR--;
                    else if(tempR>0)tempR++;
                    else if(tempC<0)tempC--;
                    else if(tempC>0)tempC++;
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