// https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=3&contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE&problemTitle=&orderBy=INQUERY_COUNT&selectCodeLang=ALL&select-1=3&pageSize=10&pageIndex=2
// [삼성SW Expert Academy] 상호의 배틀필드 : Java
import java.util.*;
import java.io.*;

class Solution
{
    static int H, W;
    static char[][] board;
    final static String startChars = "^v<>";
    final static Map<Character, int[]> directions = new HashMap<>();
    final static Map<Character, Character> cmdDirMap = new HashMap<>();
    static {
        directions.put('^', new int[] {-1, 0});
        directions.put('v', new int[] {1, 0});
        directions.put('<', new int[] {0, -1});
        directions.put('>', new int[] {0, 1});
        cmdDirMap.put('U', '^');
        cmdDirMap.put('D', 'v');
        cmdDirMap.put('L', '<');
        cmdDirMap.put('R', '>');
    }

    public static void main(String args[]) throws Exception
    {
        // 로컬 파일 비교용
        //System.setIn(new FileInputStream("res/input.txt"));
        //System.setOut(new PrintStream(new FileOutputStream("res/my_output.txt")));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append('#').append(test_case).append(' ');
            H = sc.nextInt();
            W = sc.nextInt();
            board = new char[H][];
            int[] pos = new int[2];

            for (int i = 0; i < H; i++) {
                board[i] = sc.next().toCharArray();
                for (int j = 0; j < W; j++) {
                    if (startChars.indexOf(board[i][j]) != -1) {
                        pos[0] = i;
                        pos[1] = j;
                    }
                }
            }
            int N = sc.nextInt();
            char[] commands = sc.next().toCharArray();

            for (char cmd: commands) {
                if (cmd == 'S') {
                    shoot(pos);
                    continue;
                }
                char direction = cmdDirMap.get(cmd);
                board[pos[0]][pos[1]] = direction;

                int[] drc = directions.get(direction);
                int nr = pos[0] + drc[0];
                int nc = pos[1] + drc[1];

                if (isValidBoundary(nr, nc) && board[nr][nc] == '.') {
                    board[pos[0]][pos[1]] = '.';
                    pos[0] = nr;
                    pos[1] = nc;
                    board[nr][nc] = direction;
                }
            }

            for (char[] row: board) {
                sb.append(row).append('\n');
            }
        }

        System.out.print(sb.toString());
    }

    private static void shoot(int[] pos) {
        int[] drc = directions.get(board[pos[0]][pos[1]]);
        int nr = pos[0] + drc[0];
        int nc = pos[1] + drc[1];

        while (isValidBoundary(nr, nc) && !isWall(nr, nc)) {
            nr += drc[0];
            nc += drc[1];
        }
        if (isValidBoundary(nr, nc) && board[nr][nc] == '*') {
            board[nr][nc] = '.';
        }
    }

    private static boolean isWall(int row, int col) {
        if (board[row][col] == '*' || board[row][col] == '#') {
            return true;
        }
        return false;
    }

    private static boolean isValidBoundary(int row, int col) {
        if (row < 0 || row >= H || col < 0 || col >= W) {
            return false;
        }
        return true;
    }
}