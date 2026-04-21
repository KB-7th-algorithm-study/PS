package PS.sejong.week3;
import java.util.*;
import java.io.*;

public class BOJ_1932 {
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int result = 0;

        int N = Integer.parseInt(br.readLine());
        int [][] dp = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            // 현재 위치의 최대값은 윗층 왼, 오 중에서 큰 값 + 현재 위치 값
            for(int j = 1; j <= i; j++) dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + Integer.parseInt(st.nextToken());
        }

        // 하단 층의 최대값을 구하고 출력
        for(int i = 1; i <= N; i++) result = Math.max(result, dp[N][i]);
        System.out.print(result);
    }
}
