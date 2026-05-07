package PS.minji.week5;

import java.util.Stack;

public class crane {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        // 인형 넣을 스택
        Stack<Integer> stack = new Stack<>();

        // moves를 순회하는 for문
        for(int i=0; i<moves.length; i++) {
            // moves[0]... moves에 담긴 숫자들 하나씩 꺼내서 순회
            int move = moves[i]-1;

            // moves는 가로에 해당하니 해당하는 곳에서 세로로 내려가는 for문
            for(int j=0; j<board.length; j++) {
                int doll = board[j][move];
                // 0은 뽑지 않고 있을 때까지 내려가야 함
                if(doll != 0) {
                    board[j][move] = 0;

                    // 맨 위 확인
                    if(!stack.isEmpty() && stack.peek() == doll) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(doll);
                    }
                    break;
                }


            }
        }
        return answer;
    }
}
