// https://school.programmers.co.kr/learn/courses/30/lessons/12913
// [프로그래머스] 땅따먹기 : Java
import java.util.*;

class Solution {
    int solution(int[][] land) {
        // 완탐불가 4*3^(N-1) -> dp인가?
        // 특정 셀(행, 열)의 숫자를 사용했을 때의 최대 sum값을 dp 2차원 배열에 저장
        // 다음 행의 경우 이전행의 나머지 열들의 값 중 최대값 활용 (현재 열 제외)

        int N = land.length;
        int[][] dp = new int[N][4];
        dp[0] = land[0];

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                // dp[i][j] = max(dp[i-1][], dp[i-1][], [i-1][]) + land[i][j];
                int maxSum = 0;
                for (int k = 0; k < 4; k++) {
                    if (k != j) {
                        maxSum = Math.max(maxSum, dp[i-1][k]);
                    }
                }
                dp[i][j] = maxSum + land[i][j];
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (dp[N-1][i] > answer) {
                answer = dp[N-1][i];
            }
        }

        return answer;
    }
}