package PS.sanghak.week5;

import java.util.ArrayList;
import java.util.List;

public class 프렌즈4블록 {
    class Solution {
        public int solution(int m, int n, String[] board) {
            String[][] map = new String[m][n];
            for (int i = 0; i < m; i++) {
                map[i] = board[i].split("");
            }
            int answer = 0;
            while (true) {
                List<int[]> list = new ArrayList<>();

                for (int i = 0; i < m - 1; i++) {
                    for (int j = 0; j < n - 1; j++) {
                        String str = map[i][j];
                        //""이 아니고 우, 하, 우하 가 같으면 List에 좌표값으로 일단 넣어두기.
                        if (!str.isEmpty()
                                && str.equals(map[i][j + 1])
                                && str.equals(map[i + 1][j])
                                && str.equals(map[i + 1][j + 1])) {
                            list.add(new int[]{i, j});
                            list.add(new int[]{i, j + 1});
                            list.add(new int[]{i + 1, j});
                            list.add(new int[]{i + 1, j + 1});

                        }
                    }
                }
                if (list.isEmpty()) {
                    break;
                }
                for (int i = 0; i < list.size(); i++) {
                    int[] arr = list.get(i);
                    int x = arr[0];
                    int y = arr[1];
                    map[x][y] = "";
                }

                //아래에서 위로 탐색하면서 값이 없는 공간이 나오면
                //그 위에줄에도 값이 있을때까지 while문으로 계속 올라가다가
                //칸을 벗어나면 while문을 빠져나감. 아니면 값이있는걸 아래로 내림 그러고 또 탐색
                //System.out.println(map[m-1][n-1]);
                for (int i = m - 1; i >= 0; i--) {
                    for (int j = n - 1; j >= 0; j--) {
                        if (map[i][j].isEmpty()) {
                            int x = i;
                            int y = j;
                            int k = i;
                            while (k > 0) {
                                if (!map[k - 1][j].equals("")) {
                                    map[x][y] = map[k - 1][j];
                                    map[k - 1][j] = "";
                                    break;
                                }
                                k -= 1;
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j].equals("")) {
                        answer++;
                    }
                }
            }
            return answer;
        }
    }
}
