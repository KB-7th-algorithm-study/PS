import java.util.*;

// 가로, 세로 순서로 최솟값 구하기
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] rain = new int[m * n];
        // 끝까지 비가 안 오는 칸은 모든 빗방울보다 뒤로
        Arrays.fill(rain, drops.length + 1);
        for (int i = 0; i < drops.length; i++) {
            rain[drops[i][0] * n + drops[i][1]] = i + 1;
        }

        int cols = n - w + 1;
        int[] rowMin = new int[m * cols];
        int[] deque = new int[Math.max(m, n)];

        // 각 행에서 가로 w칸 중 가장 먼저 비 오는 시간
        for (int r = 0; r < m; r++) {
            int head = 0;
            int tail = 0;
            for (int c = 0; c < n; c++) {
                while (head < tail && deque[head] <= c - w) head++;

                // 지금 칸보다 늦게 젖는 칸은 최솟값 후보에서 빼기
                while (head < tail && rain[r * n + deque[tail - 1]] >= rain[r * n + c]) {
                    tail--;
                }
                deque[tail++] = c;

                if (c >= w - 1) {
                    rowMin[r * cols + c - w + 1] = rain[r * n + deque[head]];
                }
            }
        }

        int best = -1;
        int[] answer = new int[2];

        // 가로 최솟값을 다시 세로 h줄씩 묶으면 구역 전체의 최솟값
        for (int c = 0; c < cols; c++) {
            int head = 0;
            int tail = 0;
            for (int r = 0; r < m; r++) {
                while (head < tail && deque[head] <= r - h) head++;
                while (head < tail && rowMin[deque[tail - 1] * cols + c] >= rowMin[r * cols + c]) {
                    tail--;
                }
                deque[tail++] = r;

                if (r < h - 1) continue;

                int start = r - h + 1;
                int time = rowMin[deque[head] * cols + c];
                boolean change = false;
                if (time > best) {
                    change = true;
                }
                else if (time == best) {
                    // 열부터 돌고 있어서 같은 시간이면 행도 직접 비교해야 함
                    if (start < answer[0] || (start == answer[0] && c < answer[1])) {
                        change = true;
                    }
                }

                if (change) {
                    best = time;
                    answer[0] = start;
                    answer[1] = c;
                }
            }
        }
        return answer;
    }
}

/*
// 이분탐색 + 누적합으로 확인하는 버전
// mid번째 비까지 내린 뒤에도 안 젖은 구역이 있으면 더 뒤까지 확인
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] rain = new int[m * n];
        for (int i = 0; i < drops.length; i++) {
            rain[drops[i][0] * n + drops[i][1]] = i + 1;
        }

        int size = n + 1;
        int[] sum = new int[(m + 1) * size];
        int left = 0;
        int right = drops.length;
        int[] answer = {0, 0};

        while (left <= right) {
            int mid = (left + right) / 2;

            // 매번 mid까지 젖은 칸만 1로 보고 누적합 다시 만들기
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    int value = 0;
                    int t = rain[(i - 1) * n + j - 1];
                    if (t != 0 && t <= mid) value = 1;
                    sum[i * size + j] = value + sum[(i - 1) * size + j]
                        + sum[i * size + j - 1] - sum[(i - 1) * size + j - 1];
                }
            }

            boolean found = false;
            int row = -1;
            int col = -1;
            for (int i = 0; i <= m - h; i++) {
                for (int j = 0; j <= n - w; j++) {
                    int bottom = i + h;
                    int end = j + w;
                    int count = sum[bottom * size + end] - sum[i * size + end]
                        - sum[bottom * size + j] + sum[i * size + j];

                    if (count == 0) {
                        row = i;
                        col = j;
                        found = true;
                        break;
                    }
                }
                // 안쪽 for만 빠져나오면 다음 행에서 좌표가 바뀔 수 있음
                if (found) break;
            }

            if (found) {
                answer[0] = row;
                answer[1] = col;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return answer;
    }
}
*/
