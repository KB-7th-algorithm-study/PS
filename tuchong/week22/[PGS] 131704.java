// https://school.programmers.co.kr/learn/courses/30/lessons/131704
// [프로그래머스] 택배상자 : Java
import java.util.*;

class Solution {
    public int solution(int[] order) {
        // 보조 컨테이너 벨트 = stack
        
        int answer = 0;
        int incNum = 0; // 기존 컨테이너 벨트의 숫자 - 1
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int curOrder: order) {
            while (curOrder > incNum) {
                incNum++;
                stack.push(incNum); // 일단 stack에 push
            }
            if (!stack.isEmpty() && stack.pop() == curOrder) {
                answer++;
            }
            else {
                break;
            }
        }
        
        return answer;
    }
}