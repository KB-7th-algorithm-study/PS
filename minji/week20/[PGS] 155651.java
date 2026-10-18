import java.util.*;

// [PGS] 155651 호텔 대실
class Solution {
    public int solution(String[][] book_time) {
        int n = book_time.length;
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = toMin(book_time[i][0]);
            times[i][1] = toMin(book_time[i][1]) + 10; // 청소 10분 포함
        }

        // 시작 시간 기준 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 사용 중인 방들의 '청소 끝나는 시각' 최소 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for (int[] t : times) {
            // 가장 빨리 비는 방이 이미 비었으면 재사용
            if (!pq.isEmpty() && pq.peek() <= t[0]) {
                pq.poll();
            }
            pq.offer(t[1]);
            answer = Math.max(answer, pq.size());
        }

        return answer;
    }

    private int toMin(String hhmm) {
        String[] p = hhmm.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
}
