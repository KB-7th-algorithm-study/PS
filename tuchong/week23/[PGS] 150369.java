// https://school.programmers.co.kr/learn/courses/30/lessons/150369
// [프로그래머스] 택배 배달과 수거하기 : Java

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리
        // 무조건 뒤에 있는 집부터 배달 및 수거
        // 배달/수거할 때는 cap 이하의 개수에서 최대한 많이
        
        long answer = 0;
        int deliveryRemains = 0;
        int pickupRemains = 0;
        int deliveryIndex = 0;
        int pickupIndex = 0;
        
        for (int i = 0; i < n; i++) {
            deliveryRemains += deliveries[i];
            pickupRemains += pickups[i];
            if (deliveries[i] != 0) deliveryIndex = i;
            if (pickups[i] != 0) pickupIndex = i;
        }
        
        while (deliveryRemains > 0 || pickupRemains > 0) {
            // 왕복 거리 추가
            answer += (Math.max(deliveryIndex, pickupIndex) + 1) * 2;
            
            // 배달
            int dCnt = 0;
            while (deliveryRemains > 0 && dCnt < cap) {
                if (deliveries[deliveryIndex] == 0) { 
                    deliveryIndex--;
                    continue;
                }
                int take = Math.min(deliveries[deliveryIndex], cap - dCnt);
                deliveries[deliveryIndex] -= take;
                deliveryRemains -= take;
                dCnt += take;
            }
            while (deliveryIndex > 0 && deliveries[deliveryIndex] == 0) {
                deliveryIndex--;
            }
            
            // 수거
            int pCnt = 0;
            while (pickupRemains > 0 && pCnt < cap) {
                if (pickups[pickupIndex] == 0) { 
                    pickupIndex--;
                    continue;
                }
                int take = Math.min(pickups[pickupIndex], cap - pCnt);
                pickups[pickupIndex] -= take;
                pickupRemains -= take;
                pCnt += take;
            }
            while (pickupIndex > 0 && pickups[pickupIndex] == 0) {
                pickupIndex--;
            }
        }
        
        return answer;
    }
}