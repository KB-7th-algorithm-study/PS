import java.util.*;

class Solution {
    public static int[][] map;
    public static int N, M;
    public static final int[] dirX = {-1, 1, 0, 0};
    public static final int[] dirY = {0, 0, -1, 1};
    
    public static class Coordinate {
        int x, y, dist, isJumped;
        
        public Coordinate(int x, int y, int dist, int isJumped) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.isJumped = isJumped;
        }
    }
    public int solution(int n, int m, int[][] hole) {
        int answer = 0;
        
        input(n, m, hole);

        return BFS();
    }
    
    public int BFS() {
        boolean[][][] isVisited = new boolean[N + 1][M + 1][2]; 
        LinkedList<Coordinate> que = new LinkedList<>();
        
        que.offer(new Coordinate(1, 1, 0, 1));
        
        while(!que.isEmpty()) {
            Coordinate current = que.poll();
            
            if(!isAbleCheck(current.x, current.y, current.isJumped, isVisited)) continue;
            if(current.x == N && current.y == M) {
                return current.dist;
            }
            isVisited[current.x][current.y][current.isJumped] = true;
            
            for(int i=0; i<4; i++) {
                int nextX = dirX[i] + current.x;
                int nextY = dirY[i] + current.y;
    
                que.offer(new Coordinate(nextX, nextY, current.dist + 1, current.isJumped));                
                                
                if(current.isJumped == 1) {
                    nextX += dirX[i];
                    nextY += dirY[i];
                    que.offer(new Coordinate(nextX, nextY, current.dist + 1, 0));
                }
            }
        }
        
        return -1;
    }
    
    public boolean isAbleCheck(int nextX, int nextY, int isJumped, boolean[][][] isVisited) {
        return nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= M && map[nextX][nextY] != 1 && !isVisited[nextX][nextY][isJumped];
    }
    
    public void input(int n, int m, int[][] hole) {
        N = n;
        M = m;
        map = new int[N + 1][M + 1];
        
        for(int[] t : hole) {
            int x = t[0];
            int y = t[1];
            map[x][y] = 1;
        }
    }
}