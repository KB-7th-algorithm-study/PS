import java.util.*;

// [PGS] 340212 퍼즐 게임 챌린지
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int lo = 1, hi = 1;
        for (int d : diffs) hi = Math.max(hi, d);

        // 숙련도 level 이 높을수록 총 시간이 줄어듦 → 이분 탐색
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (totalTime(diffs, times, mid) <= limit) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private long totalTime(int[] diffs, int[] times, int level) {
        long total = 0;
        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                // 틀린 횟수 = diffs[i] - level, 틀릴 때마다 이전 퍼즐도 다시 풀어야 함
                long prev = i > 0 ? times[i - 1] : 0;
                total += (long) (diffs[i] - level) * (times[i] + prev) + times[i];
            }
        }
        return total;
    }
}
