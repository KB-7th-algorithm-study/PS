class Solution {
    public int solution(String[] board) {
        String s = "";
        for(int i=0;i<3;i++){
            s += board[i];
        }
        int countO=0;
        int countX=0;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == 'O') {
                countO++;
            }
            if (s.charAt(i) == 'X') {
                countX++;
            }
        }
        if (countX == countO) {
            if (
                // 가로 3개
                board[0].equals("OOO") ||
                board[1].equals("OOO") ||
                board[2].equals("OOO") ||

                // 세로 3개
                (board[0].charAt(0) == 'O' &&
                 board[1].charAt(0) == 'O' &&
                 board[2].charAt(0) == 'O') ||

                (board[0].charAt(1) == 'O' &&
                 board[1].charAt(1) == 'O' &&
                 board[2].charAt(1) == 'O') ||

                (board[0].charAt(2) == 'O' &&
                 board[1].charAt(2) == 'O' &&
                 board[2].charAt(2) == 'O') ||

                // 대각선 2개
                (board[0].charAt(0) == 'O' &&
                 board[1].charAt(1) == 'O' &&
                 board[2].charAt(2) == 'O') ||

                (board[0].charAt(2) == 'O' &&
                 board[1].charAt(1) == 'O' &&
                 board[2].charAt(0) == 'O')
            ) {
                return 0;
            }
            else{
                return 1;
            }
        }
        else if (countX + 1 == countO) {
            if (
                // 가로 3개
                board[0].equals("XXX") ||
                board[1].equals("XXX") ||
                board[2].equals("XXX") ||

                // 세로 3개
                (board[0].charAt(0) == 'X' &&
                 board[1].charAt(0) == 'X' &&
                 board[2].charAt(0) == 'X') ||

                (board[0].charAt(1) == 'X' &&
                 board[1].charAt(1) == 'X' &&
                 board[2].charAt(1) == 'X') ||

                (board[0].charAt(2) == 'X' &&
                 board[1].charAt(2) == 'X' &&
                 board[2].charAt(2) == 'X') ||

                // 대각선 2개
                (board[0].charAt(0) == 'X' &&
                 board[1].charAt(1) == 'X' &&
                 board[2].charAt(2) == 'X') ||

                (board[0].charAt(2) == 'X' &&
                 board[1].charAt(1) == 'X' &&
                 board[2].charAt(0) == 'X')
            ) {
                return 0;
            }
            else{
                return 1;
            }
        }
        else
            return 0;
    }
}