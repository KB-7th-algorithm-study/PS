import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int n = board.length; // n*n 배열

        Stack<Integer> stack = new Stack<>();
        int answer = 0;

        // moves 순서대로 크레인 작동
        for(int i = 0; i < moves.length; i++) {
            int num = moves[i]; // 선택한 열 번호

            // 위에서부터 인형 탐색
            for(int j = 0; j < n; j++) {

                // 인형이 존재하는 경우
                if(board[j][num-1] > 0) {
                    // 스택의 top 인형과 같은 경우 제거
                    if(!stack.isEmpty() && stack.peek() == board[j][num-1]) {
                        stack.pop();
                        answer += 2; // 같은 인형 두 개가 사라짐
                    } else {
                        // 다른 인형이면 스택에 추가
                        stack.push(board[j][num-1]);
                    }

                    // 꺼낸 인형은 board에서 제거
                    board[j][num-1] = 0;

                    // 해당 열에서는 가장 위 인형만 꺼내므로 종료
                    break;
                }
            }
        }

        return answer;
    }
}