//최적 풀이 (힌트O)
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0;
        int pickup = 0;

        // 먼 집부터 확인
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];

            // 현재 집까지 와야 할 일이 남아있으면 트럭 출발
            while (deliver > 0 || pickup > 0) {
                // 한 번 갈 때 각각 cap만큼 처리 가능
                deliver -= cap;
                pickup -= cap;

                // i번째 집까지 갔다가 다시 물류창고로 돌아옴
                answer += (i + 1) * 2L;
            }
        }
        return answer;

        /*
        //그대로 시뮬
        long answer = 0;
        int dIdx = n - 1;
        int pIdx = n - 1;

        while (true) {
            // 이미 처리한 집은 앞으로 당기기
            while (dIdx >= 0 && deliveries[dIdx] == 0) {
                dIdx--;
            }
            while (pIdx >= 0 && pickups[pIdx] == 0) {
                pIdx--;
            }

            // 둘 다 끝났으면 종료
            if (dIdx < 0 && pIdx < 0) break;

            int far = Math.max(dIdx, pIdx);
            answer += (far + 1) * 2L;
            int box = cap;

            // 먼 집부터 배달
            for (int i = dIdx; i >= 0 && box > 0; i--) {
                if (deliveries[i] <= box) {
                    box -= deliveries[i];
                    deliveries[i] = 0;
                }
                else {
                    deliveries[i] -= box;
                    box = 0;
                }
            }

            box = cap;

            // 돌아오면서 먼 집부터 수거
            for (int i = pIdx; i >= 0 && box > 0; i--) {
                if (pickups[i] <= box) {
                    box -= pickups[i];
                    pickups[i] = 0;
                }
                else {
                    pickups[i] -= box;
                    box = 0;
                }
            }
        }
        return answer;
        */
    }
}
