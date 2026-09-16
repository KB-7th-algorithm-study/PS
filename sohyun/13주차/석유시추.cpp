#include <vector>
#include <queue>
#include <set>
#include <algorithm>

using namespace std;

int n, m;
int visited[501][501];
int oilSize[250005];
int dy[] = {-1, 1, 0, 0};
int dx[] = {0, 0, -1, 1};

int bfs(int sy, int sx, int id, const vector<vector<int>>& land) {
    queue<pair<int, int>> q;
    q.push({sy, sx});
    visited[sy][sx] = id;
    int size = 1;

    while(!q.empty()) {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= 0 && ny < n && nx >= 0 && nx < m) {
                if(land[ny][nx] == 1 && visited[ny][nx] == 0) {
                    visited[ny][nx] = id;
                    q.push({ny, nx});
                    size++;
                }
            }
        }
    }
    return size;
}

int solution(vector<vector<int>> land) {
    n = land.size();
    m = land[0].size();
    
    int oilId = 1;
    
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            if(land[i][j] == 1 && visited[i][j] == 0) {
                oilSize[oilId] = bfs(i, j, oilId, land);
                oilId++;
            }
        }
    }

    int maxOil = 0;

    for(int x = 0; x < m; x++) {
        set<int> uniqueOils; 
        int currentOilSum = 0;

        for(int y = 0; y < n; y++) {
            if(visited[y][x] > 0) {
                uniqueOils.insert(visited[y][x]);
            }
        }

        for(int id : uniqueOils) {
            currentOilSum += oilSize[id];
        }

        maxOil = max(maxOil, currentOilSum);
    }

    return maxOil;
}