import java.io.*;
import java.util.*;

// [SEA] 1873 상호의 배틀필드
class Solution {
    // 방향 순서: 위, 아래, 왼쪽, 오른쪽 (명령 U, D, L, R 과 같은 순서)
    static final int[] DR = {-1, 1, 0, 0};
    static final int[] DC = {0, 0, -1, 1};
    static final char[] TANK = {'^', 'v', '<', '>'};
    static final String MOVE = "UDLR";

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(nextLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(nextLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][];
            int r = 0, c = 0, dir = 0;
            for (int i = 0; i < h; i++) {
                map[i] = nextLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    int d = new String(TANK).indexOf(map[i][j]);
                    if (d >= 0) { r = i; c = j; dir = d; }
                }
            }

            nextLine(); // 입력 개수 N (명령 문자열 길이로 대신 사용)
            String commands = nextLine();

            for (char cmd : commands.toCharArray()) {
                if (cmd == 'S') {
                    // 포탄은 벽에 부딪히거나 맵 밖으로 나갈 때까지 직진 (물 위는 통과)
                    int sr = r + DR[dir], sc = c + DC[dir];
                    while (sr >= 0 && sr < h && sc >= 0 && sc < w) {
                        if (map[sr][sc] == '*') {
                            map[sr][sc] = '.';
                            break;
                        }
                        if (map[sr][sc] == '#') break;
                        sr += DR[dir];
                        sc += DC[dir];
                    }
                } else {
                    // 이동하지 못하더라도 방향은 바뀜
                    dir = MOVE.indexOf(cmd);
                    int nr = r + DR[dir], nc = c + DC[dir];
                    if (nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] == '.') {
                        map[r][c] = '.';
                        r = nr;
                        c = nc;
                    }
                    map[r][c] = TANK[dir];
                }
            }

            sb.append('#').append(tc).append(' ');
            for (int i = 0; i < h; i++) {
                sb.append(map[i]).append('\n');
            }
        }
        System.out.print(sb);
    }

    // 빈 줄은 건너뛰고 다음 줄 읽기
    static String nextLine() throws IOException {
        String line = br.readLine();
        while (line != null && line.trim().isEmpty()) line = br.readLine();
        return line.trim();
    }
}
