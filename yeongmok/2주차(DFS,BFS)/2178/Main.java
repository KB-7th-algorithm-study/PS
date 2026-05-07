import java.util.*;

public class Main{
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int N, M;
    static boolean[][] visited;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            String s = sc.next();
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(s.substring(j,j+1));
            }
        }

        BFS(0,0);

        System.out.println(map[N-1][M-1]);
    }

    private static void BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        int[] start = {i,j};

        visited[i][j] = true;
        queue.add(start);

        while(!queue.isEmpty()){
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            for(int d=0;d<4;d++){
                int row = r+dr[d];
                int col = c+dc[d];
                if(row>=0&&row<N&&col>=0&&col<M){
                    if(!visited[row][col] && map[row][col]!=0){
                        visited[row][col] = true;
                        int[] temp = {row,col};
                        map[row][col] = map[now[0]][now[1]]+1;

                        queue.add(temp);
                    }
                }
            }
        }
     }
}