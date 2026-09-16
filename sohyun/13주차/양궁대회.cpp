#include <vector>
#include <algorithm>

using namespace std;

int maxDiff = 0;
vector<int> answer = {-1};
vector<int> ryan(11, 0);

bool isBetter(const vector<int>& ryan) {
    for (int i = 10; i >= 0; i--) {
        if (ryan[i] > answer[i]) return true;
        if (ryan[i] < answer[i]) return false;
    }
    return false;
}

void dfs(int idx, int arrowsLeft, const vector<int>& apeach) {
    // 0점 과녁까지 모두 결정했거나 화살을 다 쓴 경우
    if (idx == 11 || arrowsLeft == 0) {
        // 남은 화살이 존재 -> 0점 과녁에 모두 쏘기
        if (arrowsLeft > 0) {
            ryan[10] += arrowsLeft;
        }

        // 점수 계산
        int ryanScore = 0;
        int apeachScore = 0;
        for (int i = 0; i < 11; i++) {
            if (ryan[i] == 0 && apeach[i] == 0) continue;
            if (ryan[i] > apeach[i]) {
                ryanScore += (10 - i);
            } else {
                apeachScore += (10 - i);
            }
        }

        int diff = ryanScore - apeachScore;
        // 라이언이 이김 && 기존 최대 점수 차이보다 큰 경우
        if (diff > 0 && diff > maxDiff) {
            maxDiff = diff;
            answer = ryan;
        } 
        // 점수 차이가 같은 경우
        else if (diff > 0 && diff == maxDiff) {
            if (isBetter(ryan)) {
                answer = ryan;
            }
        }

        // 0점에 몰아줬던 화살 빼기
        if (arrowsLeft > 0) {
            ryan[10] -= arrowsLeft;
        }
        return;
    }

    // 라이언이 해당 과녁 점수를 가져가는 경우
    int need = apeach[idx] + 1;
    if (arrowsLeft >= need) {
        ryan[idx] = need;
        dfs(idx + 1, arrowsLeft - need, apeach);
        ryan[idx] = 0;
    }

    // 해당 과녁 점수를 포기하는 경우
    dfs(idx + 1, arrowsLeft, apeach);
}

vector<int> solution(int n, vector<int> info) {
    dfs(0, n, info);
    return answer;
}