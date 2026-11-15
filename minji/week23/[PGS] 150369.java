// [PGS] 150369 택배 배달과 수거하기
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0; // 아직 처리하지 못한 배달 상자 수
        int pickup = 0;  // 아직 처리하지 못한 수거 상자 수

        // 가장 먼 집부터 처리: 먼 집을 갈 때 가까운 집 일도 함께 처리
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];

            // 남은 일이 있으면 i+1번째 집까지 왕복, 한 번에 cap개씩 처리
            while (deliver > 0 || pickup > 0) {
                deliver -= cap;
                pickup -= cap;
                answer += (long) (i + 1) * 2;
            }
        }
        return answer;
    }
}
