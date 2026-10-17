// https://school.programmers.co.kr/learn/courses/30/lessons/160585
// [프로그래머스] 혼자서 하는 틱택토 : Java

class Solution {
    public int solution(String[] board) {
        // X의 개수 = O의 개수 or O의 개수 - 1 이어야 함
        // 게임이 끝난 상황(한 줄 완성)일 때 더 진행되면 안됨
        
        int[][] convBoard = new int[3][3];
        int oCnt = 0;
        int xCnt = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cell = board[i].charAt(j);
                if (cell == 'O') {
                    convBoard[i][j] = 1;
                    oCnt++;
                }
                else if (cell == 'X') {
                    convBoard[i][j] = 2;
                    xCnt++;
                }
                else if (cell == '.') {
                    convBoard[i][j] = 0;
                }
            }
        }
        
        if (xCnt < oCnt -1 || xCnt > oCnt) {
            return 0;
        }
        
        // 행
        for (int i = 0; i < 3; i++) {
            if (convBoard[i][0] == convBoard[i][1] && convBoard[i][1] == convBoard[i][2]) {            
                if (!isValidResult(i, xCnt, oCnt, convBoard)) return 0;
            }
        }
        
        // 열
        for (int i = 0; i < 3; i++) {
            if (convBoard[0][i] == convBoard[1][i] && convBoard[1][i] == convBoard[2][i]) {            
                if (!isValidResult(i, xCnt, oCnt, convBoard)) return 0;
            }
        }
        
        // 대각선
        if (convBoard[0][0] == convBoard[1][1] && convBoard[1][1] == convBoard[2][2]) {
            if (!isValidResult(1, xCnt, oCnt, convBoard)) return 0;
        }
        if (convBoard[0][2] == convBoard[1][1] && convBoard[1][1] == convBoard[2][0]) {
            if (!isValidResult(1, xCnt, oCnt, convBoard)) return 0;
        }
        
        return 1;
    }
    
    public boolean isValidResult(int rep, int xCnt, int oCnt, int[][] convBoard) {
        if (convBoard[rep][rep] == 1) {
            if (xCnt == oCnt) return false;
        }
        if (convBoard[rep][rep] == 2) {
            if (oCnt == xCnt + 1) return false;
        }
        return true;
    }
}