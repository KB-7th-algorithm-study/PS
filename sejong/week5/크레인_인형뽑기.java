import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        // 이동
        for(int move : moves){
            // 최상단 인형 최신화
            int second = stack.isEmpty() ? 0 : stack.peek();
            
            // 해당 위치 인형을 순서대로 검사
            boolean isDoll = false;
            for(int i = 0; i < board[move - 1].length; i++){
                int doll = board[i][move - 1];
                // 맨 위 인형을 바구니로 옮김
                if(doll != 0){
                    stack.push(doll);
                    board[i][move - 1] = 0;
                    isDoll = true;
                    break;
                }
            }
            // 바구니에 인형을 옮겼는지 여부 & 비교할 인형이 있는지 검사
            int first = stack.isEmpty() ? 0 : stack.peek();
            if(isDoll && second != 0){
                // 2개 연속이면 바구니에서 꺼내기
                if(first == second){
                    stack.pop();
                    stack.pop();
                    answer += 2;
                }
            }
        }
        
        return answer;
    }
}