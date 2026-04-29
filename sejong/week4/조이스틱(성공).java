// gemini가 풀어줌
class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        
        // 초기 최솟값: 오른쪽으로만 쭉 가는 경우
        int minMove = len - 1; 

        for (int i = 0; i < len; i++) {
            // 1. 수직 이동 횟수 (상하 조작)
            // 아스키코드를 이용해 위로 가는 것과 아래로 가는 것 중 최솟값 더하기
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            // 2. 수평 이동 횟수 (좌우 조작)
            // 현재 위치(i) 바로 다음부터 시작하는 연속된 'A' 덩어리를 지나쳐서, 
            // 그 다음으로 바꿔야 할 알파벳의 위치(next)를 찾습니다.
            int next = i + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // [핵심 로직] 3가지 이동 경로 중 가장 짧은 것을 선택합니다.
            // 경로 1: 기존에 구한 최솟값 (초기값은 직진 n)
            // 경로 2: 0부터 i까지 우측으로 이동 후, 다시 0으로 돌아가서 좌측으로 next까지 이동
            //        거리 = (i * 2) + (len - next)
            // 경로 3: 처음부터 좌측으로 next까지 이동 후, 다시 0으로 돌아와서 우측으로 i까지 이동
            //        거리 = (len - next) * 2 + i
            
            minMove = Math.min(minMove, (i * 2) + len - next);
            minMove = Math.min(minMove, (len - next) * 2 + i);
        }

        // 수직 이동 횟수 총합에 최솟값 수평 이동 횟수를 더함
        answer += minMove;
        
        return answer;
    }
}