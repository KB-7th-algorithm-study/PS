import java.util.*;

class Solution {
  public int solution(String[][] bookTime) {

    int[][] times = new int[bookTime.length][2];

    // 시간 전부 분으로 바꾸기
    for (int i = 0; i < bookTime.length; i++) {
      String[] in = bookTime[i][0].split(":");
      String[] out = bookTime[i][1].split(":");

      int inTime = Integer.parseInt(in[0]) * 60
          + Integer.parseInt(in[1]);

      // 퇴실 후 청소시간 10분까지 포함
      int outTime = Integer.parseInt(out[0]) * 60
          + Integer.parseInt(out[1]) + 10;

      times[i][0] = inTime;
      times[i][1] = outTime;
    }

    // 입실 시간 빠른 순서대로 정렬
    Arrays.sort(times, (a, b) -> a[0] - b[0]);

    // 현재 쓰고 있는 방들의 퇴실(+청소) 시간 저장
    // 가장 빨리 비는 방이 맨 앞에 오도록
    PriorityQueue<Integer> room = new PriorityQueue<>();

    for (int i = 0; i < times.length; i++) {

      int nowIn = times[i][0];
      int nowOut = times[i][1];

      // 현재 입실 전에 비었다면 재사용 가능
      if (!room.isEmpty() && room.peek() <= nowIn) {
        room.poll();
      }

      // 현재 예약이 사용하는 방의 종료 시간 추가
      room.offer(nowOut);
    }

    return room.size();
  }
}
