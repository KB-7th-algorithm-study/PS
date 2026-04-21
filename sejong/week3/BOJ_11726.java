package PS.sejong.week3;
import java.io.*;

public class BOJ_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        if (N < 3) System.out.print(N);
        else {
            int[] dp = new int[N + 1];
            dp[1] = 1;
            dp[2] = 2;
            // dp[n] = dp[n - 1] + dp[n - 2];
            for (int i = 3; i <= N; i++) dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
            System.out.print(dp[N]);
        }
    }    
}
