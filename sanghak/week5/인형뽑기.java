package PS.sanghak.week5;

import java.util.Stack;

public class 인형뽑기 {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int a = 0; a < moves.length; a++) {
            int s = moves[a];
            for (int i = 0; i < board.length; i++) {
                if (board[i][s - 1] > 0) {
                    stack.push(board[i][s - 1]);
                    board[i][s - 1] = 0;
                    for (int z = 0; z < stack.size(); z++) {
                        if (z + 1 < stack.size()) {
                            if (stack.get(z) == stack.get(z + 1)) {
                                answer += 2;
                                stack.pop();
                                stack.pop();
                            }
                        }
                    }
                    break;
                }
            }
        }



        return answer;
    }
}
