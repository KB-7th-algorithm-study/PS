// https://school.programmers.co.kr/learn/courses/30/lessons/468379
// [프로그래머스] 선인장 숨기기 : Java
import java.util.*;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        // 최솟값 슬라이딩 윈도우 활용하기
        // 뒤쪽: 최솟값이 될 수 없는 후보 제거 (오름차순 유지)
        // 앞쪽: 구간 밖으로 나간 인덱스 제거
        // -> 양쪽 끝에서 모두 제거해야 하므로 Deque 사용

        int[][] dropBoard = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dropBoard[i], 500_001);
        }
        for (int i = 0; i < drops.length; i++) {
            dropBoard[drops[i][0]][drops[i][1]] = i + 1;
        }

        // 가로처리: rowMins[i][j] = dropBoard[i][j ~ j+w-1] 구간에서의 최솟값
        int[][] rowMins = new int[m][];
        for (int i = 0; i < m; i++) {
            rowMins[i] = slidingMin(dropBoard[i], w);
        }

        // 세로처리: areaMins[i][j] = dropBoard[i][j] ~ dropBoard[i+h-1][j+w-1] 구역의 최솟값
        int[][] areaMins = new int[m - h + 1][n - w + 1];
        int[] rowMinsCol = new int[m];
        for (int j = 0; j <= n - w; j++) {
            for (int i = 0; i < m; i++) {
                rowMinsCol[i] = rowMins[i][j]; // rowMins의 j열을 1차원 배열로 꺼냄
            }
            int[] areaMinsCol = slidingMin(rowMinsCol, h); // areaMinsCol[i] = 행 i ~ i+h-1, 열 j ~ j+w-1 직사각형의 최솟값
            for (int i = 0; i <= m - h; i++) {
                areaMins[i][j] = areaMinsCol[i];
            }
        }

        // 최솟값이 가장 큰 위치 찾기
        int maxOrder = areaMins[0][0];
        int[] answer = new int[2];
        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                if (areaMins[i][j] > maxOrder) {
                    maxOrder = areaMins[i][j];
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }
        return answer;
    }

    private int[] slidingMin(int[] arr, int windowLen) {
        int[] result = new int[arr.length - windowLen + 1];
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            // 덱 뒤쪽 후보가 현재값보다 큰 경우 -> 앞으로 최솟값이 될 수 없음
            // 덱 뒤쪽 후보가 현재값과 같은 경우 -> 구간에서의 최솟값만 구하면 되므로 더 오래 살아남는 뒤쪽 인덱스만 남기기
            while (!dq.isEmpty() && arr[dq.peekLast()] >= arr[i]) {
                dq.pollLast();
            }

            // 현재 인덱스 넣기
            dq.offerLast(i);

            // 구간 범위 조건
            if (dq.peekFirst() <= i - windowLen) {
                dq.pollFirst();
            }

            // [i - windowLen + 1, i] 구간에서의 최솟값을 result[i - windowLen + 1]에 저장
            if (i >= windowLen - 1) {
                result[i - windowLen + 1] = arr[dq.peekFirst()];
            }
        }
        return result;
    }
}


/* 1차 풀이 - 시간초과
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        // 2가지 경우 나눠서 판단
        // 빗방울을 맞지 않는 위치가 있는 경우
        // 그렇지 않은 경우

        boolean[][] dropBoardB = new boolean[m][n];
        int[][] dropBoardI = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dropBoardI[i], 500_001);
        }
        for (int i = 0; i < drops.length; i++) {
            dropBoardB[drops[i][0]][drops[i][1]] = true;
            dropBoardI[drops[i][0]][drops[i][1]] = i + 1;
        }

        // 빗방울을 맞지 않는 위치가 있는 경우
        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                if (checkNoDrop(i, j, h, w, dropBoardB)) {
                    return new int[] {i, j};
                }
            }
        }

        // 빗방울을 맞지 않는 위치가 없는 경우
        int maxOrder = getOrder(0, 0, h, w, dropBoardI);
        int[] answer = new int[2];

        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                int curOrder = getOrder(i, j, h, w, dropBoardI);
                if (curOrder > maxOrder) {
                    maxOrder = curOrder;
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }

        return answer;
    }

    private int getOrder(int r, int c, int h, int w, int[][] dropBoardI) {
        int minOrder = dropBoardI[r][c];

        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                minOrder = Math.min(minOrder, dropBoardI[i][j]);
            }
        }

        return minOrder;
    }

    private boolean checkNoDrop(int r, int c, int h, int w, boolean[][] dropBoardB) {
        for (int i = r; i < r + h; i++) {
            for (int j = c; j < c + w; j++) {
                if (dropBoardB[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
*/