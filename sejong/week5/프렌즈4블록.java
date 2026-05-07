import java.util.*;
class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        // board -> 이차원 배열로 변경
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            // 2x2 검사
            List<int[]> list = new LinkedList<>();
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char v = map[i][j];
                    if (v != 'X' && v == map[i+1][j] && v == map[i+1][j+1] && v == map[i][j+1]) {
                        list.add(new int[]{i, j, i+1, j, i+1, j+1, i, j+1});
                    }
                }
            }
            
            // 시뮬레이션 종료
            if (list.isEmpty()) break;
            
            // 2x2인 곳들 n으로 체크
            for (int[] i : list) {
                for (int k = 0; k < i.length; k += 2) {
                    map[i[k]][i[k+1]] = 'n';
                }
            }
            
            // N인 곳만 개수 세고 X로 변환
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 'n') {
                        answer++;
                        map[i][j] = 'X';
                    }
                }
            }
            
            // 중력 적용
            for (int j = 0; j < n; j++) {
                int empty = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != 'X') {
                        map[empty][j] = map[i][j];
                        if (empty != i) map[i][j] = 'X';
                        empty--;
                    }
                }
                while (empty >= 0) {
                    map[empty][j] = 'X';
                    empty--;
                }
            }
        }
        
        return answer;
    }
}