
import java.util.*;

class Solution {

  // 시간을 분 단위로 변환
  public int toMinute(String time) {
    String[] temp = time.split(":");
    return Integer.parseInt(temp[0]) * 60
        + Integer.parseInt(temp[1]);
  }

  // 누적 주차 시간으로 요금 계산
  public int calculateFee(int useTime, int[] fees) {
    if (useTime <= fees[0]) return fees[1];

    return fees[1]
        + (int) Math.ceil((double) (useTime - fees[0]) / fees[2])
        * fees[3];
  }

  public int[] solution(int[] fees, String[] records) {

    Map<Integer, Integer> inMap = new HashMap<>();
    Map<Integer, Integer> timeMap = new TreeMap<>();

    for (String record : records) {
      String[] elements = record.split(" ");
      int carNumber = Integer.parseInt(elements[1]);
      int time = toMinute(elements[0]);

      if (elements[2].equals("IN")) {
        inMap.put(carNumber, time);
      }
      else {
        int inTime = inMap.get(carNumber);

        // 요금이 아닌 주차 시간을 누적
        timeMap.put(carNumber,
            timeMap.getOrDefault(carNumber, 0)
                + (time - inTime));

        inMap.remove(carNumber);
      }
    }

    // 출차 기록이 없는 차량은 23:59 출차 처리
    for (Map.Entry<Integer, Integer> entry : inMap.entrySet()) {
      int carNumber = entry.getKey();
      int inTime = entry.getValue();

      timeMap.put(carNumber,
          timeMap.getOrDefault(carNumber, 0)
              + (1439 - inTime));
    }

    // 차량 번호 오름차순으로 요금 계산
    int[] result = new int[timeMap.size()];
    int idx = 0;

    for (int useTime : timeMap.values()) {
      result[idx++] = calculateFee(useTime, fees);
    }

    return result;
  }
}
