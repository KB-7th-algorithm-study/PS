import java.lang.Math;

class Solution {
    public static int solution(String name) {
        int answer = 0;

        //상하 움직임 계산(아스키 코드로 계산)
        for(int i=0;i<name.length();i++){ 
            int n = (int) name.charAt(i);
            if(n<=78) answer += n-65;
            else answer += 91-n;
        }

        // 좌우 움직임 계산
        char[] charArr = name.toCharArray();

        // 0번 위치에서 시작해서 남은 문자들을 모두 처리하는 최소 이동거리
        answer += dfs(0, charArr);

        return answer;
    }

    // 현재 위치 j에서 시작해서 남은 문자들을 모두 A로 만드는 최소 이동거리
    public static int dfs(int j, char[] charArr) {
        // 현재 위치는 처리했다고 표시
        charArr[j] = 'A';

        int[][] dist = new int[4][2];
        dist = calc(dist, j, charArr);

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            if (dist[i][0] > 0) {
                int move = dist[i][0];
                int next = dist[i][1];

                // 현재 상태를 복사해서, 이 후보로 이동하는 경우를 따로 계산
                char[] copyArr = charArr.clone();

                // 이번 이동 비용 + 다음 위치에서 남은 전체 최소 비용
                int total = move + dfs(next, copyArr);

                min = Math.min(min, total);
            }
        }

        // 더 이상 이동할 곳이 없으면 이동 비용 0
        if (min == Integer.MAX_VALUE) {
            return 0;
        }

        return min;
    }

    public static int[][] calc(int[][] dist, int j, char[] charArr) {
        // 기본 오른쪽 탐색
        for (int i = j + 1; i < charArr.length; i++) {
            if (charArr[i] != 'A') {
                dist[0][0] = i - j;
                dist[0][1] = i;
                break;
            }
        }

        // 왼쪽으로 돌아서 오른쪽 끝 탐색
        for (int i = charArr.length - 1; i > j; i--) {
            if (charArr[i] != 'A') {
                dist[1][0] = j + (charArr.length - i);
                dist[1][1] = i;
                break;
            }
        }

        // 기본 왼쪽 탐색
        for (int i = j - 1; i >= 0; i--) {
            if (charArr[i] != 'A') {
                dist[2][0] = j - i;
                dist[2][1] = i;
                break;
            }
        }

        // 오른쪽으로 돌아서 왼쪽 끝 탐색
        for (int i = 0; i < j; i++) {
            if (charArr[i] != 'A') {
                dist[3][0] = (charArr.length - j) + i;
                dist[3][1] = i;
                break;
            }
        }

        return dist;
    }
}