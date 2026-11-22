// [PGS] 468379 선인장 숨기기
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        // time[r * n + c]: 해당 칸에 비가 떨어지는 순서 (끝까지 안 떨어지면 최댓값)
        int[] time = new int[m * n];
        java.util.Arrays.fill(time, Integer.MAX_VALUE);
        for (int i = 0; i < drops.length; i++) {
            time[drops[i][0] * n + drops[i][1]] = i + 1;
        }

        // 구역이 처음 비를 맞는 시점 = 구역 안 칸들의 최솟값
        // 1단계: 각 행에서 가로 w칸 구간의 최솟값 (슬라이딩 윈도우 + 덱)
        int cols = n - w + 1;
        int[] rowMin = new int[m * cols];
        int[] dq = new int[Math.max(m, n)];
        for (int r = 0; r < m; r++) {
            int head = 0, tail = 0, base = r * n;
            for (int c = 0; c < n; c++) {
                while (tail > head && time[base + dq[tail - 1]] >= time[base + c]) tail--;
                dq[tail++] = c;
                if (dq[head] <= c - w) head++;
                if (c >= w - 1) rowMin[r * cols + c - w + 1] = time[base + dq[head]];
            }
        }

        // 2단계: 1단계 결과에서 세로 h칸 구간의 최솟값 → h x w 구역의 최솟값
        int rows = m - h + 1;
        int[] areaMin = new int[rows * cols];
        for (int c = 0; c < cols; c++) {
            int head = 0, tail = 0;
            for (int r = 0; r < m; r++) {
                while (tail > head && rowMin[dq[tail - 1] * cols + c] >= rowMin[r * cols + c]) tail--;
                dq[tail++] = r;
                if (dq[head] <= r - h) head++;
                if (r >= h - 1) areaMin[(r - h + 1) * cols + c] = rowMin[dq[head] * cols + c];
            }
        }

        // 가장 늦게 젖는 구역 선택, 같으면 위쪽 행 → 왼쪽 열 우선 (더 클 때만 갱신)
        int bestR = 0, bestC = 0, best = -1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (areaMin[r * cols + c] > best) {
                    best = areaMin[r * cols + c];
                    bestR = r;
                    bestC = c;
                }
            }
        }
        return new int[]{bestR, bestC};
    }
}
