package PS.minji.week3;

import java.io.*;

public class tiling2xn_11726 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            // 직사각형 크기 입력 받기
            int n = Integer.parseInt(br.readLine());

            if (n == 1) {
                bw.write("1\n");
                bw.flush();
                bw.close();
                return;
            }

            int[] dp = new int[n+1];

            dp[1] = 1;
            dp[2] = 2;

            for(int i=3; i<dp.length; i++) {
                dp[i] = (dp[i-1] + dp[i-2]) % 10007;
            }

            bw.write(String.valueOf(dp[n]));
            bw.flush();
            bw.close();
        }
}
