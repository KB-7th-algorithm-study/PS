import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int[][] times = new int[book_time.length][2];
        
        for (int i = 0; i < book_time.length; i++) {
            for (int j = 0; j < 2; j++) {
                String[] time = book_time[i][j].split(":");

                int hour = Integer.parseInt(time[0]);
                int minute = Integer.parseInt(time[1]);

                times[i][j] = hour * 60 + minute;
                if (j == 1) {
                    times[i][j] += 10;
                }
            }
        }
        // 두 번째 값 기준 오름차순 정렬
        Arrays.sort(times, (a, b) -> Integer.compare(a[0], b[0]));
        int[] rooms = new int[times.length];
        int answer = 0;

        for (int i = 0; i < times.length; i++) {
            boolean found = false;

            for (int j = 0; j < answer; j++) {
                if (times[i][0] >= rooms[j]) {
                    rooms[j] = times[i][1]; // 기존 방 재사용
                    found = true;
                    break;
                }
            }

            if (!found) {
                rooms[answer] = times[i][1]; // 새 방 추가
                answer++;
            }
        }
        return answer;
    }
}