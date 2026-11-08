// [PGS] 92342 양궁대회
class Solution {
    private int maxDiff = 0;
    private int[] answer = {-1};

    public int[] solution(int n, int[] info) {
        dfs(0, n, new int[11], info);
        return answer;
    }

    // idx: 과녁 인덱스(0 = 10점), left: 남은 화살 수
    private void dfs(int idx, int left, int[] ryan, int[] info) {
        if (idx == 10) {
            // 남은 화살은 모두 0점에 쏘기 (낮은 점수를 많이 맞힌 경우 우선)
            ryan[10] = left;
            evaluate(ryan, info);
            ryan[10] = 0;
            return;
        }

        // 해당 점수를 가져가려면 어피치보다 1발 더 쏘면 충분
        if (left > info[idx]) {
            ryan[idx] = info[idx] + 1;
            dfs(idx + 1, left - ryan[idx], ryan, info);
            ryan[idx] = 0;
        }
        // 해당 점수를 포기
        dfs(idx + 1, left, ryan, info);
    }

    private void evaluate(int[] ryan, int[] info) {
        int ryanScore = 0, apeachScore = 0;
        for (int i = 0; i <= 10; i++) {
            if (ryan[i] > info[i]) ryanScore += 10 - i;
            else if (info[i] > 0) apeachScore += 10 - i;
        }

        int diff = ryanScore - apeachScore;
        if (diff <= 0) return;
        if (diff > maxDiff || (diff == maxDiff && hasMoreLowScores(ryan, answer))) {
            maxDiff = diff;
            answer = ryan.clone();
        }
    }

    // 가장 낮은 점수부터 비교해 더 많이 맞힌 쪽이 우선
    private boolean hasMoreLowScores(int[] a, int[] b) {
        for (int i = 10; i >= 0; i--) {
            if (a[i] != b[i]) return a[i] > b[i];
        }
        return false;
    }
}
