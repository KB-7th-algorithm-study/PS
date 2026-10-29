// https://school.programmers.co.kr/learn/courses/30/lessons/92341
// [프로그래머스] 주차 요금 계산 : Java

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 차량별 누적 주차 시간을 저장하기 위한 Map<carNum, List[시각정보, ... ]>
        // 차량별 요금을 List[{차량번호, 계산된 요금}, ...] 에 저장후 번호 기준 정렬
        
        Map<Integer, List<Integer>> carInOutHist = new HashMap<>();
        
        for (String record: records) {
            String[] recordInfo = record.split(" ");
            int time = toMinuteTime(recordInfo[0]);
            int carNum = Integer.parseInt(recordInfo[1]);
            
            carInOutHist.putIfAbsent(carNum, new ArrayList<>());
            carInOutHist.get(carNum).add(time);
        }
        
        List<int[]> carFees = new ArrayList<>();
        
        for (int carNum: carInOutHist.keySet()) {
            List<Integer> hist = carInOutHist.get(carNum);
            int timeSum = 0;
            int feeSum = fees[1];
            
            if (hist.size() % 2 == 1) {
                hist.add(toMinuteTime("23:59"));
            }
            for (int j = 1; j < hist.size(); j += 2) {
                timeSum += hist.get(j) - hist.get(j - 1);
            }
            
            if (timeSum > fees[0]) {
                feeSum += Math.ceil((double)(timeSum - fees[0]) / fees[2]) * fees[3];
            }
            carFees.add(new int[] {carNum, feeSum});
        }
        
        Collections.sort(carFees, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] answer = new int[carFees.size()];
        
        for (int i = 0; i < carFees.size(); i++) {
            answer[i] = carFees.get(i)[1];
        }
        
        return answer;
    }
    
    private int toMinuteTime(String s) {
        String[] hourAndMinute = s.split(":");
        int hour = Integer.parseInt(hourAndMinute[0]);
        int minute = Integer.parseInt(hourAndMinute[1]);
        return hour * 60 + minute;
    }
}