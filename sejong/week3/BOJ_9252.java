import java.util.*;
import java.io.*;

public class Main {
    static char[] ca1;
    static char[] ca2;
    static boolean[] v1;
    static boolean[] v2;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = br.readLine();
        String s2 = br.readLine();

        int N = s1.length();
        int M = s2.length();

        ca1 = new char[N + 1];
        for (int i = 0; i < N; i++) ca1[i + 1] = s1.charAt(i);
        ca2 = new char[M + 1];
        for (int i = 0; i < M; i++) ca2[i + 1] = s2.charAt(i);
        v1 = new boolean[N + 1];
        v2 = new boolean[M + 1];

        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (ca1[i] == ca2[j]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        System.out.print(dp[N][M] + "\n");
        if (dp[N][M] != 0) {
            bt(N, M);
            System.out.print(sb.reverse());
        }
    }

    static void bt(int N, int M) {
        if (N == 0 || M == 0) return;

        if (ca1[N] == ca2[M]) {
            sb.append(ca1[N]);
            bt(N - 1, M - 1);
        } else {
            if (dp[N - 1][M] > dp[N][M - 1]) bt(N - 1, M);
            else bt(N, M - 1);
        }
    }
}