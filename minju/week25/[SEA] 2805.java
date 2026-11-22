import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] farm = new int[n][n];

            for (int i = 0; i < n; i++) {
                String line = br.readLine().trim();

                // 숫자가 붙어서 들어오니까 한 글자씩 저장
                for (int j = 0; j < n; j++) {
                    farm[i][j] = line.charAt(j) - '0';
                }
            }

            int mid = n / 2;
            int answer = 0;
            int left = mid;
            int right = mid;

            // 위쪽은 가운데 한 칸에서 시작해서 양옆으로 넓어짐
            // 가운데 줄도 여기서 더하기
            for (int i = 0; i <= mid; i++) {
                for (int j = left; j <= right; j++) {
                    answer += farm[i][j];
                }

                left--;
                right++;
            }

            // 가운데 줄은 이미 다 더했으니까 그 다음 줄부터
            // n이 5면 아래쪽 범위는 1~3, 그 다음은 2~2
            left = 1;
            right = n - 2;

            for (int i = mid + 1; i < n; i++) {
                for (int j = left; j <= right; j++) {
                    answer += farm[i][j];
                }

                left++;
                right--;
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}
