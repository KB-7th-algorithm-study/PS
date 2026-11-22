import java.util.Arrays;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] rain = new int[m * n];
        Arrays.fill(rain, drops.length + 1);
        for (int i = 0; i < drops.length; i++) {
            rain[drops[i][0] * n + drops[i][1]] = i + 1;
        }

        int cols = n - w + 1;
        int[] rowMin = new int[m * cols];
        int[] deque = new int[Math.max(m, n)];

        for (int r = 0; r < m; r++) {
            int head = 0;
            int tail = 0;
            for (int c = 0; c < n; c++) {
                while (head < tail && deque[head] <= c - w) {
                    head++;
                }
                while (head < tail
                        && rain[r * n + deque[tail - 1]] >= rain[r * n + c]) {
                    tail--;
                }
                deque[tail++] = c;
                if (c >= w - 1) {
                    rowMin[r * cols + c - w + 1] = rain[r * n + deque[head]];
                }
            }
        }

        int best = -1;
        int[] answer = {0, 0};

        for (int c = 0; c < cols; c++) {
            int head = 0;
            int tail = 0;
            for (int r = 0; r < m; r++) {
                while (head < tail && deque[head] <= r - h) {
                    head++;
                }
                while (head < tail
                        && rowMin[deque[tail - 1] * cols + c] >= rowMin[r * cols + c]) {
                    tail--;
                }
                deque[tail++] = r;
                if (r >= h - 1) {
                    int top = r - h + 1;
                    int firstRain = rowMin[deque[head] * cols + c];
                    if (firstRain > best
                            || (firstRain == best
                            && (top < answer[0] || (top == answer[0] && c < answer[1])))) {
                        best = firstRain;
                        answer[0] = top;
                        answer[1] = c;
                    }
                }
            }
        }
        return answer;
    }
}