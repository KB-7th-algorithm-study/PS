import java.util.*;

// [PGS] 160585 혼자서 하는 틱택토
class Solution {
    public int solution(String[] board) {
        int o = 0, x = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'O') o++;
                else if (c == 'X') x++;
            }
        }

        // O가 선공: O == X 또는 O == X + 1 만 가능
        if (o != x && o != x + 1) return 0;

        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');

        // 둘 다 이긴 상태는 불가능
        if (oWin && xWin) return 0;
        // O가 이겼으면 O가 마지막 수 → O == X + 1
        if (oWin && o != x + 1) return 0;
        // X가 이겼으면 X가 마지막 수 → O == X
        if (xWin && o != x) return 0;

        return 1;
    }

    private boolean isWin(String[] b, char c) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (b[i].charAt(0) == c && b[i].charAt(1) == c && b[i].charAt(2) == c) return true;
            // 세로
            if (b[0].charAt(i) == c && b[1].charAt(i) == c && b[2].charAt(i) == c) return true;
        }
        // 대각선
        if (b[0].charAt(0) == c && b[1].charAt(1) == c && b[2].charAt(2) == c) return true;
        if (b[0].charAt(2) == c && b[1].charAt(1) == c && b[2].charAt(0) == c) return true;
        return false;
    }
}
