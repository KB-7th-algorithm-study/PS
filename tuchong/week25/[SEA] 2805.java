// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
// [삼성SW Expert Academy] 농작물 수확하기 : Java
import java.util.*;
import java.io.*;

class Solution
{
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
            // row별로 어떤 인덱스의 col을 더하면 될지 판단
            // mid = N/2 (중심 인덱스), 2 * mid = N - 1
            // 0 -> mid
            // 1 -> (mid - 1, mid + 1)
            // ...
            // mid -> (mid - mid, mid + mid)
            // mid + 1 -> (mid + 1 - mid, 2 * mid - (mid + 1 - mid))
            // mid + 2 -> (mid + 2 - mid, 2 * mid - (mid + 2 - mid))

            sb.append('#').append(test_case).append(' ');

            int N = sc.nextInt();
            char[][] board = new char[N][];

            for (int i = 0; i < N; i++) {
                board[i] = sc.next().toCharArray();
            }

            int sum = 0;
            int mid = N / 2;

            for (int i = 0; i <= mid; i++) {
                for (int j = mid - i; j <= mid + i; j++) {
                    sum += board[i][j] - '0';
                }
            }
            for (int i = mid + 1; i < N; i++) {
                for (int j = i - mid; j <= 2 * mid - (i - mid); j++) {
                    sum += board[i][j] - '0';
                }
            }

            sb.append(sum).append('\n');
        }

        System.out.print(sb.toString());
    }
}