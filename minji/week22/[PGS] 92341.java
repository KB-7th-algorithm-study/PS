import java.util.*;

// [PGS] 92341 주차 요금 계산
class Solution {
    private static final int LAST_TIME = 23 * 60 + 59;

    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTime = new HashMap<>();
        // 차량 번호가 작은 순서로 꺼내기 위해 TreeMap 사용
        Map<String, Integer> total = new TreeMap<>();

        for (String record : records) {
            String[] p = record.split(" ");
            int time = toMin(p[0]);
            if (p[2].equals("IN")) {
                inTime.put(p[1], time);
            } else {
                total.merge(p[1], time - inTime.remove(p[1]), Integer::sum);
            }
        }

        // 출차 기록이 없는 차량은 23:59 출차로 처리
        for (Map.Entry<String, Integer> e : inTime.entrySet()) {
            total.merge(e.getKey(), LAST_TIME - e.getValue(), Integer::sum);
        }

        int[] answer = new int[total.size()];
        int idx = 0;
        for (int minutes : total.values()) {
            answer[idx++] = calcFee(fees, minutes);
        }
        return answer;
    }

    private int calcFee(int[] fees, int minutes) {
        if (minutes <= fees[0]) return fees[1];
        // 초과 시간은 단위 시간으로 올림
        int units = (minutes - fees[0] + fees[2] - 1) / fees[2];
        return fees[1] + units * fees[3];
    }

    private int toMin(String hhmm) {
        String[] p = hhmm.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
}
