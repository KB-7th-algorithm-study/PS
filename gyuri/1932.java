import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        arr = new int[size][size]; // 삼각형 정보
        dp = new int[size][size]; // 계산 결과 저장

        // 삼각형 입력 받기
        for(int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int j = 0;

            // 해당 줄의 값들을 배열에 저장
            while(st.hasMoreTokens()) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
        }

        // dp 초기화
        for(int i = 0; i < size; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0,0));
    }

    public static int dfs(int x, int y) {
        // 마지막 줄 도착하면 더 내려갈 수 없으므로 현재 값 반환
        if(x == arr.length - 1) return arr[x][y];

        // 이미 계산한 위치면 저장된 값 그대로 사용
        if(dp[x][y] != -1) return dp[x][y];

        int left = dfs(x + 1, y);       // 같은 열
        int right = dfs(x + 1, y + 1);  // 오른쪽 열

        // 현재값 + 두 경로 중 큰 값 저장
        dp[x][y] = arr[x][y] + Math.max(left, right);

        return dp[x][y];
    }
}
