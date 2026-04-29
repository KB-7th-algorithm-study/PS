#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int N, M;
vector<string> maze;
int dist[101][101];

int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};

void bfs(int startY, int startX) {
    queue<pair<int, int>> q;
    q.push({startY, startX});
    dist[startY][startX] = 1;

    while (!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                if (maze[ny][nx] == '1' && dist[ny][nx] == 0) {
                    dist[ny][nx] = dist[y][x] + 1;
                    q.push({ny, nx});
                }
            }
        }
    }
}

int main() {
    cin >> N >> M;
    string row = "";
    for (int i = 0; i < N; i++) {
        cin >> row;
        maze.push_back(row);
    }

    bfs(0, 0);

    cout << dist[N-1][M-1] << endl;

    return 0;
}