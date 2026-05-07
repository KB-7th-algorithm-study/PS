#include <string>
#include <vector>
using namespace std;

int solution(int m, int n, vector<string> board) {
    int answer = 0;

    while (true) {
        vector<vector<bool>> is_marked(m, vector<bool>(n, false));
        bool found = false;

        // 2x2 블록 찾기
        for (int i = 0; i < m - 1; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (board[i][j] == ' ') continue;
                
                char target = board[i][j];
                if (board[i + 1][j] == target && board[i][j + 1] == target && board[i + 1][j + 1] == target) {
                    is_marked[i][j] = is_marked[i + 1][j] = is_marked[i][j + 1] = is_marked[i + 1][j + 1] = true;
                    found = true;
                }
            }
        }
        if (!found) break;

        // 블록 지우고 개수 세기
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (is_marked[i][j]) {
                    answer++;
                    board[i][j] = ' ';
                }
            }
        }
        // 블록 아래로 떨어뜨리기
        for (int j = 0; j < n; ++j) {
            for (int i = m - 1; i >= 0; --i) {
                if (board[i][j] == ' ') {
                    for (int k = i - 1; k >= 0; --k) {
                        if (board[k][j] != ' ') {
                            board[i][j] = board[k][j];
                            board[k][j] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }

    return answer;
}