// https://school.programmers.co.kr/learn/courses/30/lessons/155651
// [프로그래머스] 호텔 대실 : Java 
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 최소 객실 수 구하기
        // 입실 시간 기준 정렬, 퇴실 시간 기준 PQ
        
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int roomCnt = 1;
        pq.offer(toMinTime(book_time[0][1]));
        
        for (int i = 1; i < book_time.length; i++) {
            int startTime = toMinTime(book_time[i][0]);
            int endTime = toMinTime(book_time[i][1]);
          
            // pq에서 가장 빨리 끝나는 시각과 새로운 startTime 비교
            if (pq.peek() <= startTime - 10) {
                pq.poll();
            }
            else {
                roomCnt++;
            }
            
            pq.offer(endTime);
        }
        
        return roomCnt;
    }
    
    private int toMinTime(String time) {
        String[] hourAndMin = time.split(":");
        return Integer.parseInt(hourAndMin[0]) * 60 + Integer.parseInt(hourAndMin[1]);
    }
}