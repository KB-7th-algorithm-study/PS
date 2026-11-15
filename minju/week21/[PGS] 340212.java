class Solution {

  public int solution(int[] diffs, int[] times, long limit) {

    int left = 1;
    int right = 0;

    for (int diff : diffs) {
      right = Math.max(right, diff);
    }

    int answer = right;

    // 숙련도를 이분 탐색
    while (left <= right) {

      int mid = (left + right) / 2;
      long count = 0;

      // 현재 숙련도로 모든 퍼즐을 푸는 데 걸리는 시간
      for (int i = 0; i < diffs.length; i++) {

        if (diffs[i] <= mid) {
          count += times[i];
        } else {

          int prev = (i == 0) ? 0 : times[i - 1];

          // 틀린 횟수만큼 이전 + 현재 퍼즐 시간 추가
          count += (long)(diffs[i] - mid)
              * (times[i] + prev) + times[i];
        }

        // 제한시간 초과하면 더 계산할 필요 없음
        if (count > limit) break;
      }

      if (count <= limit) {
        // 성공했으므로 숙련도를 더 낮춰보기
        answer = mid;
        right = mid - 1;
      } else {
        // 시간 초과이므로 숙련도를 높이기
        left = mid + 1;
      }
    }

    return answer;
  }
}
