// https://school.programmers.co.kr/learn/courses/30/lessons/92342
// [프로그래머스] 양궁대회 : Java

class Solution {
    private int n;
    private int maxDiff;
    private int[] info;
    private int[] answer;

    public int[] solution(int n, int[] info) {
        // 가장 큰 점수 차이로 이기기 위해서 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지
        
        this.n = n;
        this.info = info;
        maxDiff = 0;
        answer = new int[]{-1};

        dfs(0, 0, new int[11]);
        
        return answer;
    }

    private void dfs(int curIdx, int cnt, int[] curArr) {
        if (curIdx == 11) {
            int arrowRemains = n - cnt;
            curArr[10] += arrowRemains; // 남은 화살은 0점에
            evaluate(curArr);
            curArr[10] -= arrowRemains;
            return;
        }

        // 이 과녁 이기기
        if (cnt + info[curIdx] + 1 <= n) {
            curArr[curIdx] = info[curIdx] + 1;
            dfs(curIdx + 1, cnt + info[curIdx] + 1, curArr);
            curArr[curIdx] = 0;
        }
        // 이 과녁 포기
        dfs(curIdx + 1, cnt, curArr);
    }

    private void evaluate(int[] curArr) {
        int ryan = 0;
        int apeach = 0;
        
        for (int i = 0; i < 11; i++) {
            if (curArr[i] > info[i]) {
                ryan += 10 - i;
            }
            else if (info[i] > 0) {
                apeach += 10 - i;
            }
        }
        
        int diff = ryan - apeach;
        if (diff <= 0) return;

        if (diff > maxDiff || (diff == maxDiff && isBetter(curArr))) {
            maxDiff = diff;
            answer = curArr.clone();
        }
    }

    // 낮은 점수부터 비교해서 더 많이 맞힌 쪽이 우선
    private boolean isBetter(int[] curArr) {
        for (int i = 10; i >= 0; i--) {
            if (curArr[i] != answer[i]) {
                return curArr[i] > answer[i];
            }
        }
        return false;
    }
}