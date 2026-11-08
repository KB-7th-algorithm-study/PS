import java.util.*;

class Solution {
    int max = 0;
    int[] answer = { -1 };

    void dfs(int idx, int arrow, int[] info, int[] now) {
        // 0점까지 결정했으면 점수 계산
        if (idx == 11) {
            // 남은 화살은 0점에 다 넣어도 점수에는 영향 없음
            if (arrow > 0) {
                now[10] += arrow;
            }

            int lionScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < 11; i++) {
                if (now[i] == 0 && info[i] == 0) continue;

                if (now[i] > info[i]) {
                    lionScore += 10 - i;
                }
                else {
                    apeachScore += 10 - i;
                }
            }

            int gap = lionScore - apeachScore;

            if (gap > 0 && gap >= max) {
                // 더 큰 점수차면 바로 갱신
                if (gap > max) {
                    max = gap;
                    answer = now.clone();
                }
                // 점수차 같으면 낮은 점수를 더 많이 맞춘 경우
                else {
                    for (int i = 10; i >= 0; i--) {
                        if (now[i] == answer[i]) continue;

                        if (now[i] > answer[i]) {
                            answer = now.clone();
                        }
                        break;
                    }
                }
            }

            // 백트래킹 때문에 다시 빼주기
            if (arrow > 0) {
                now[10] -= arrow;
            }
            return;
        }

        // 1. 현재 점수를 라이언이 가져가는 경우
        int use = info[idx] + 1;

        if (arrow >= use) {
            now[idx] = use;
            dfs(idx + 1, arrow - use, info, now);
            now[idx] = 0;
        }

        // 2. 현재 점수를 포기하는 경우
        dfs(idx + 1, arrow, info, now);
    }

    public int[] solution(int n, int[] info) {
        int[] now = new int[11];
        dfs(0, n, info, now);
        return answer;
    }
}

// import java.util.*;

// class Solution {
//     int max = 0;
//     int[] answer = {-1};

//     void dfs(int idx, int left, int[] info, int[] lion) {
//         if (idx == 11) {
//             // 화살 남으면 그냥 0점에 몰아주기
//             lion[10] += left;

//             int apeach = 0;
//             int ryan = 0;

//             for (int i = 0; i < 11; i++) {
//                 if (info[i] == 0 && lion[i] == 0) continue;

//                 if (lion[i] > info[i]) {
//                     ryan += 10 - i;
//                 }
//                 else {
//                     apeach += 10 - i;
//                 }
//             }

//             int diff = ryan - apeach;

//             if (diff > 0) {
//                 if (diff > max) {
//                     max = diff;
//                     answer = lion.clone();
//                 }
//                 // 점수차 같은 경우 낮은 점수 많이 맞춘걸로
//                 else if (diff == max) {
//                     for (int i = 10; i >= 0; i--) {
//                         if (lion[i] > answer[i]) {
//                             answer = lion.clone();
//                             break;
//                         }
//                         else if (lion[i] < answer[i]) {
//                             break;
//                         }
//                     }
//                 }
//             }

//             lion[10] -= left;
//             return;
//         }

//         // 현재 점수를 먹기
//         int need = info[idx] + 1;

//         if (left >= need) {
//             lion[idx] = need;
//             dfs(idx + 1, left - need, info, lion);
//             // 다시 원래대로
//             lion[idx] = 0;
//         }

//         // 현재 점수 안먹기
//         dfs(idx + 1, left, info, lion);
//     }

//     public int[] solution(int n, int[] info) {
//         int[] lion = new int[11];
//         dfs(0, n, info, lion);
//         return answer;
//     }
// }
