import java.util.*;

// [PGS] 131704 택배상자
class Solution {
    public int solution(int[] order) {
        // 보조 컨테이너 벨트는 마지막에 넣은 상자부터 꺼내므로 스택
        Deque<Integer> sub = new ArrayDeque<>();
        int box = 1; // 메인 벨트의 맨 앞 상자
        int answer = 0;

        for (int target : order) {
            // 실어야 할 상자가 나올 때까지 메인 벨트 상자를 보조 벨트로 이동
            while (box <= target) {
                sub.push(box++);
            }
            // 보조 벨트 맨 앞이 목표 상자가 아니면 더 이상 실을 수 없음
            if (sub.peek() != target) break;
            sub.pop();
            answer++;
        }
        return answer;
    }
}
