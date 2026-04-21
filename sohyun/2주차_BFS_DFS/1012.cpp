#include <iostream>
#include <vector>
#include <stack>
#include <cstring> // 배열 초기화를 위해

using namespace std;

int M, N, K;
int baechu[51][51];
bool visited[51][51];

int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};

void dfs(int startX, int startY) {
    stack<pair<int, int>> s;
    s.push({startX, startY}); // 시작좌표
    visited[startX][startY] = true;

    while (!s.empty()) {
        int x = s.top().first; // 현재 x위치
        int y = s.top().second;// 현재 y위치
        s.pop();

        for (int i = 0; i < 4; i++) { // 상하좌우 체크
            int nx = x + dx[i]; // 다음 x좌표
            int ny = y + dy[i]; // 다음 y좌표

            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (baechu[nx][ny] == 1 && !visited[nx][ny]) { // 배추가 있는데 아직 방문X
                    visited[nx][ny] = true;
                    s.push({nx, ny});
                }
            }
        }
    }
}

int main() {
    int T;
    cin >> T;
    while (T--) {
        cin >> M >> N >> K;
        memset(baechu, 0, sizeof(baechu));
        memset(visited, false, sizeof(visited));

        for (int i = 0; i < K; i++) {
            int x, y;
            cin >> x >> y;
            baechu[x][y] = 1;
        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (baechu[i][j] == 1 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        cout << cnt << endl;
    }
    return 0;
}